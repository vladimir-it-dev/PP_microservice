package com.bank.profile.entity;

import com.bank.profile.serializer.AccountDetailsIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 *Класс AccountDetailsId представляет собой информацию о счёте (у одного профиля может быть много счетов),
 * которая сохраняется в таблице account_details_id.
 * AccountDetailsId связан отношением Many-to-one с сущностью Profile.
 * Для сериализации спользуется AccountDetailsIdSerializer
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = AccountDetailsIdSerializer.class)
@Table(name = "account_details_id")
public class AccountDetailsId extends BaseEntity {

    @NotEmpty(message = "Технический идентификатор не может быть пустым")
    @Column(name = "account_id")
    private Long accountId;

    @NotEmpty(message = "Профиль не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile owner;

}
