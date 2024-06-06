package com.bank.account.Entity;

import com.bank.account.Audit.AuditListener;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Класс AccountDetails представляет информацию о банковском счете,
 * включая идентификатор паспорта владельца, уникальный идентификатор счета,
 * идентификатор банковских реквизитов, текущий баланс на счете и флаг, указывающий,
 * является ли баланс отрицательным.
 * Этот класс также относится к сущностям JPA, хранящимся в таблице "account_details".
 * <p>
 * Класс содержит следующие аннотации:
 *
 * @Data - заменяет все методы геттеров и сеттеров, а также методы toString, equals и hashCode.
 * @Builder - позволяет использовать паттерн Builder для создания объектов класса.
 * @NoArgsConstructor - добавляет конструктор без аргументов.
 * @AllArgsConstructor - добавляет конструктор со всеми аргументами.
 * @Entity - указывает, что класс является сущностью JPA и будет обрабатываться Hibernate.
 * @Table(name = "account_details") - указывает на имя таблицы в базе данных для данной сущности.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(description = "Данные об аккаунте")
@Table(name = "account_details", schema = "account")
@EntityListeners(AuditListener.class)
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "passport_id")
    private Long passportId;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "bank_details_id")
    private Long bankDetailsId;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "negative_balance")
    private Boolean negativeBalance;

    @Column(name = "profile_id")
    private Long profileId;

}