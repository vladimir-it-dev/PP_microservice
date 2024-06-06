package com.bank.profile.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;

/**
 *Класс Audit предназначен для сохранения в таблице audit информации о классе, над которым была проведена
 * операция, виде операции (создание, редактирование, удаление), пользователе, произведшем операцию,
 * а также сохранено представление объекта в формате JSON до и после редактирования.
 * Если операция связана с созданием новой сущности, а не с модификацией существующей, то поля modifiedBy,
 * modifiedAt и newEntityJson могут и должны быть пустыми.
 */
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "entity_type")
    private String entityType;

    @NotEmpty
    @Column(name = "operation_type")
    private String operationType;

    @NotEmpty
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "modified_at")
    private OffsetDateTime modifiedAt;

    @Column(name = "new_entity_json")
    private String newEntityJson;

    @NotEmpty
    @Column(name = "entity_json")
    private String entityJson;
}
