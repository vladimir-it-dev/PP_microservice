package com.bank.antifraud.service;

import com.bank.antifraud.config.FraudProperties;
import com.bank.antifraud.dto.AntiFraudDto;
import com.bank.antifraud.dto.temp.TransferDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfer;
import com.bank.antifraud.mapper.TransferMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransferRepository;
import com.bank.antifraud.util.ServiceClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneFraudService extends BaseFraudService implements FraudService<SuspiciousPhoneTransfer> {

    final SuspiciousPhoneTransferRepository phoneTransferRepository;
    protected PhoneFraudService(FraudProperties properties, ServiceClient serviceClient,
                                SuspiciousPhoneTransferRepository suspiciousPhoneTransferRepository,
                                TransferMapper transferMapper) {
        super(properties, serviceClient, transferMapper);
        this.phoneTransferRepository = suspiciousPhoneTransferRepository;
    }

    @Override
    public AntiFraudDto check(TransferDto transferDto) {
        SuspiciousPhoneTransfer suspiciousAccountTransfer = SuspiciousPhoneTransfer.builder()
                .phoneTransferId(transferDto.getId()).build();
        proceed(suspiciousAccountTransfer, transferDto);
        phoneTransferRepository.save(suspiciousAccountTransfer);
        return getTransferMapper().phoneTransferToDto(suspiciousAccountTransfer);
    }

    @Override
    public AntiFraudDto getById(long id) {
        return getTransferMapper().phoneTransferToDto(Optional.of(phoneTransferRepository.getReferenceById(id))
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void deleteById(long id) {
        phoneTransferRepository.deleteById(id);
    }

    @Override
    public AntiFraudDto update(AntiFraudDto antiFraudDto) {
        return getTransferMapper().phoneTransferToDto(
                phoneTransferRepository.save(getTransferMapper().dtoToPhoneTransfer(antiFraudDto)));
    }
}
