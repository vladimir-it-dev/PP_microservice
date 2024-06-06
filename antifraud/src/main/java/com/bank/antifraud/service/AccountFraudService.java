package com.bank.antifraud.service;

import com.bank.antifraud.config.FraudProperties;
import com.bank.antifraud.dto.AntiFraudDto;
import com.bank.antifraud.dto.temp.TransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import com.bank.antifraud.mapper.TransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
import com.bank.antifraud.util.ServiceClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountFraudService extends BaseFraudService implements FraudService<SuspiciousAccountTransfer> {
    final SuspiciousAccountTransferRepository accountTransferRepository;

    protected AccountFraudService(FraudProperties properties, ServiceClient serviceClient,
                                  SuspiciousAccountTransferRepository accountTransferRepository,
                                  TransferMapper transferMapper) {
        super(properties, serviceClient, transferMapper);
        this.accountTransferRepository = accountTransferRepository;
    }

    @Override
    public AntiFraudDto check(TransferDto transferDto) {
        SuspiciousAccountTransfer suspiciousAccountTransfer = SuspiciousAccountTransfer.builder()
                .accountTransferId(transferDto.getId()).build();
        proceed(suspiciousAccountTransfer, transferDto);
        accountTransferRepository.save(suspiciousAccountTransfer);
        return getTransferMapper().accountTransferToDto(suspiciousAccountTransfer);
    }

    @Override
    public AntiFraudDto getById(long id) {
        return getTransferMapper().accountTransferToDto(Optional.of(accountTransferRepository.getReferenceById(id))
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void deleteById(long id) {
        accountTransferRepository.deleteById(id);
    }

    @Override
    public AntiFraudDto update(AntiFraudDto entityDto) {
        return getTransferMapper().accountTransferToDto(
                accountTransferRepository.save(getTransferMapper().dtoToAccountTransfer(entityDto)));
    }

}
