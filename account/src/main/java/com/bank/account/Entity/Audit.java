package com.bank.account.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Audit представляет собой сущность аудита операций счета.
 * Он содержит информацию о типе сущности, типе операции, пользователе, который создал и модифицировал запись,
 * дате создания и изменения записи, а также сериализованные JSON-представления новой и измененной сущности.
 * <p>
 * Класс содержит следующие аннотации:
 *
 * @Data - заменяет все методы геттеров и сеттеров, а также методы toString, equals и hashCode.
 * @Builder - позволяет использовать паттерн Builder для создания объектов класса.
 * @NoArgsConstructor - добавляет конструктор без аргументов.
 * @AllArgsConstructor - добавляет конструктор со всеми аргументами.
 * @Entity - указывает, что класс является сущностью JPA и будет обрабатываться Hibernate.
 * @Table(name = "account_audit") - указывает на имя таблицы в базе данных для данной сущности.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "new_entity_json")
    private String newEntityJson;

    @Column(name = "entity_json")
    private String entityJson;


}
