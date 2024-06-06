package com.bank.antifraud.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AntiFraudDto{
    Long id;
    @Positive Long transferId;
    @NotNull Boolean isSuspicious;
    @NotNull Boolean isBlocked;
    @NotNull String suspiciousReason;
    String blockedReason;
}
