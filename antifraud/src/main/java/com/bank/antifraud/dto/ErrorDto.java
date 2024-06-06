package com.bank.antifraud.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorDto {
    List<String> errors;
}
