package com.bank.antifraud.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "audit")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String entityType;
    String operationType;
    String createdBy;
    String modifiedBy;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String newEntityJson;
    String entityJson;

}
