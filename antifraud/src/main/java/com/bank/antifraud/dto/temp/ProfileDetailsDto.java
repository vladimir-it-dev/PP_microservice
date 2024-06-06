package com.bank.antifraud.dto.temp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

//Профиль со счетами
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileDetailsDto {
    Long profileId;
    List<Long> accountIds;
    Long transferActualAccount;
}
