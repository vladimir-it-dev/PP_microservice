package com.bank.antifraud.service;

import com.bank.antifraud.config.FraudProperties;
import com.bank.antifraud.dto.temp.ProfileDetailsDto;
import com.bank.antifraud.dto.temp.TransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.exception.AntiFraudErrorCode;
import com.bank.antifraud.exception.BlockedException;
import com.bank.antifraud.exception.SuspiciousException;
import com.bank.antifraud.mapper.TransferMapper;
import com.bank.antifraud.util.ServiceClient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseFraudService {
    final FraudProperties properties;
    final ServiceClient serviceClient;
    @Getter final TransferMapper transferMapper;

    protected BaseFraudService(FraudProperties properties, ServiceClient serviceClient, TransferMapper transferMapper) {
        this.properties = properties;
        this.serviceClient = serviceClient;
        this.transferMapper = transferMapper;
    }

    protected void proceed(SuspiciousTransfer suspiciousTransfer, TransferDto transferDto) {
        try {
            checkTransfer(transferDto);
        } catch (BlockedException e) {
            suspiciousTransfer.setBlocked(true);
            suspiciousTransfer.setBlockedReason(e.getErrorCode().getErrorCode());

        } catch (SuspiciousException e) {
            suspiciousTransfer.setSuspicious(true);
            suspiciousTransfer.setSuspiciousReason(e.getErrorCode().getErrorCode());
        }
    }
    private void checkTransfer(TransferDto dto) throws BlockedException, SuspiciousException {
        ProfileDetailsDto profileDetails = serviceClient.getProfileDetailsById(dto.getDetailsId());
        List<TransferDto> transferDtoList = serviceClient.getTransfersByAccountId(profileDetails.getTransferActualAccount());

        checkAuth(profileDetails);
        checkLimits(dto, transferDtoList);
        checkIncomingCount(dto);
        checkRules(dto, transferDtoList, profileDetails);
    }

    private void checkLimits(TransferDto dto, List<TransferDto> transferDtoList) throws BlockedException {
        double dayAmount = transferDtoList.stream().filter((x) -> x.getDateTime().isAfter(LocalDateTime.now().minusDays(1)))
                .mapToDouble(TransferDto::getAmount).sum();
        double monthAmount = transferDtoList.stream().filter((x) -> x.getDateTime().getMonth().equals(LocalDateTime.now().getMonth()))
                .mapToDouble(TransferDto::getAmount).sum();
        if (dayAmount + dto.getAmount() >= properties.getDayLimit()) {
            throw BlockedException.builder().errorCode(AntiFraudErrorCode.DAY_LIMIT).build();
        }
        if (monthAmount + dto.getAmount() >= properties.getMonthLimit()) {
            throw BlockedException.builder().errorCode(AntiFraudErrorCode.MONTH_LIMIT).build();
        }
    }

    private void checkAuth(ProfileDetailsDto dto) throws BlockedException {
        if (!serviceClient.isUserAuth(dto.getProfileId())) {
            throw BlockedException.builder().errorCode(AntiFraudErrorCode.NOT_AUTHENTICATED).build();
        }
    }

    private void checkIncomingCount(TransferDto dto) throws BlockedException {
        if (serviceClient.getIncomingTransfersByTransferDetails(dto.getNumber()).stream()
                .filter((x) -> x.getDateTime().isAfter(LocalDateTime.now().minusDays(1)))
                .count() >= properties.getRecipientCount()) {
            throw BlockedException.builder().errorCode(AntiFraudErrorCode.TOO_MANY_INCOMING_TRANSFERS).build();
        }
    }

    private void checkRules(TransferDto dto, List<TransferDto> transferDtoList, ProfileDetailsDto profile) throws SuspiciousException {
        if (transferDtoList.isEmpty()) {
            throw SuspiciousException.builder().errorCode(AntiFraudErrorCode.FIRST_TRANSFER).build();
        }
        if (dto.getAmount() > properties.getOnetimePurchase()) {
            throw SuspiciousException.builder().errorCode(AntiFraudErrorCode.PURCHASE_LIMIT).build();
        }
        if (dto.getAmount()/3 >= transferDtoList.stream().mapToDouble(TransferDto::getAmount).sum() / transferDtoList.size()) {
            throw SuspiciousException.builder().errorCode(AntiFraudErrorCode.AVERAGE_LIMIT).build();
        }
        if (serviceClient.hasBlockedTransfersPerDay(profile.getProfileId())) {
            throw SuspiciousException.builder().errorCode(AntiFraudErrorCode.HAS_BLOCKED_TRANSFERS).build();
        }
    }
}
