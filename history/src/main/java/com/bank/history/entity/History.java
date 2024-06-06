package com.bank.history.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * History class предназначен для получения объекта и сохранения объекта в базу данных.
 */
@Entity
@Table(name = "history", schema = "history")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
