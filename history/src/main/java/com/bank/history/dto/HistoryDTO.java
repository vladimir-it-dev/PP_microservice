package com.bank.history.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

/**
 * HistoryDTO class предназначен для получения объекта и отправки объекта данных клиенту.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {
    Long id;
    @NotNull
    Long transfer_audit_id;
    @NotNull
    Long profile_audit_id;
    @NotNull
    Long account_audit_id;
    @NotNull
    Long anti_fraud_audit_id;
    @NotNull
    Long public_bank_info_audit_id;
    @NotNull
    Long authorization_audit_id;
}
