package com.bank.antifraud.dto.temp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

//Любой объект transfer-микросервиса
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferDto{
    @Positive Long id;
    @Positive Long number;
    @Positive Double amount;
    String purpose;
    @Positive Long detailsId;
    @NotNull LocalDateTime dateTime;
}
