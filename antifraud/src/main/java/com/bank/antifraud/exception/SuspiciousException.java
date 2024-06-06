package com.bank.antifraud.exception;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
public class SuspiciousException extends Exception{
    AntiFraudErrorCode errorCode;
}
