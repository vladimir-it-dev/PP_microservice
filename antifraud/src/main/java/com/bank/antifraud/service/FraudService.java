package com.bank.antifraud.service;


import com.bank.antifraud.dto.AntiFraudDto;
import com.bank.antifraud.dto.temp.TransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;

public interface FraudService<T extends SuspiciousTransfer> {
    AntiFraudDto check(TransferDto transferDto);
    AntiFraudDto getById(long id);
    void deleteById(long id);
    AntiFraudDto update(AntiFraudDto antiFraudDto);

}
