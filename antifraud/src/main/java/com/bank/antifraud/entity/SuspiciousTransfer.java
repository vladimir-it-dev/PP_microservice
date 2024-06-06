package com.bank.antifraud.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@SuperBuilder
public abstract class SuspiciousTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    boolean isBlocked;

    boolean isSuspicious;

    String blockedReason;

    @NotBlank String suspiciousReason;
}
