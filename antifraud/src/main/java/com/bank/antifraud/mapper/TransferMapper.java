package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.AntiFraudDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfer;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.entity.SuspiciousPhoneTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    @Mapping(target = "isSuspicious", source = "suspicious")
    @Mapping(target = "isBlocked", source = "blocked")
    @Mapping(target = "transferId", source = "accountTransferId")
    AntiFraudDto accountTransferToDto(SuspiciousAccountTransfer transfer);

    @Mapping(target = "isSuspicious", source = "suspicious")
    @Mapping(target = "isBlocked", source = "blocked")
    @Mapping(target = "transferId", source = "cardTransferId")
    AntiFraudDto cardTransferToDto(SuspiciousCardTransfer transfer);

    @Mapping(target = "isSuspicious", source = "suspicious")
    @Mapping(target = "isBlocked", source = "blocked")
    @Mapping(target = "transferId", source = "phoneTransferId")
    AntiFraudDto phoneTransferToDto(SuspiciousPhoneTransfer transfer);

    @Mapping(target = "accountTransferId", source = "transferId")
    SuspiciousAccountTransfer dtoToAccountTransfer(AntiFraudDto dto);

    @Mapping(target = "phoneTransferId", source = "transferId")
    SuspiciousPhoneTransfer dtoToPhoneTransfer(AntiFraudDto dto);

    @Mapping(target = "cardTransferId", source = "transferId")
    SuspiciousCardTransfer dtoToCardTransfer(AntiFraudDto dto);
}
