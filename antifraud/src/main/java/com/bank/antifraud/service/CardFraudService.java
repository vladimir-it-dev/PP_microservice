package com.bank.antifraud.service;

import com.bank.antifraud.config.FraudProperties;
import com.bank.antifraud.dto.AntiFraudDto;
import com.bank.antifraud.dto.temp.TransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.mapper.TransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import com.bank.antifraud.util.ServiceClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardFraudService extends BaseFraudService implements FraudService<SuspiciousCardTransfer> {
    final SuspiciousCardTransferRepository cardTransferRepository;
    protected CardFraudService(FraudProperties properties, ServiceClient serviceClient,
                               SuspiciousCardTransferRepository cardTransferRepository,
                               TransferMapper transferMapper) {
        super(properties, serviceClient, transferMapper);
        this.cardTransferRepository = cardTransferRepository;
    }

    @Override
    public AntiFraudDto check(TransferDto transferDto) {
        SuspiciousCardTransfer suspiciousAccountTransfer = SuspiciousCardTransfer.builder()
                .cardTransferId(transferDto.getId()).build();
        proceed(suspiciousAccountTransfer, transferDto);
        cardTransferRepository.save(suspiciousAccountTransfer);
        return getTransferMapper().cardTransferToDto(suspiciousAccountTransfer);
    }

    @Override
    public AntiFraudDto getById(long id) {
        return getTransferMapper().cardTransferToDto(Optional.of(cardTransferRepository.getReferenceById(id))
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void deleteById(long id) {
        cardTransferRepository.deleteById(id);
    }

    @Override
    public AntiFraudDto update(AntiFraudDto entity) {
        return getTransferMapper().cardTransferToDto(
                cardTransferRepository.save(getTransferMapper().dtoToCardTransfer(entity)));
    }
}
