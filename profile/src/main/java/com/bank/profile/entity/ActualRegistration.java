package com.bank.profile.entity;

import com.bank.profile.serializer.ActualRegistrationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *Класс ActualRegistration представляет собой информацию об актуальной регистрации пользователя,
 * которая сохраняется в таблице actual_registration.
 * Registration связан отношением One-to-one с сущностью Profile, может иметь пустое
 * значение поля profile.
 * Для сериализации спользуется ActualRegistrationSerializer
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = ActualRegistrationSerializer.class)
@Table(name = "actual_registration")
public class ActualRegistration extends BaseEntity {

    @NotEmpty(message = "Страна не может быть пустой")
    @Size(min = 4, max = 166, message = "Название страны должно быть длиннее 4 и короче 166 символов")
    private String country;

    @Size(min = 4, max = 160, message = "Название региона должно быть длиннее 4 и короче 160 символов")
    private String region;

    @Size(max = 160, message = "Название города должно быть не длиннее 160 символов")
    private String city;

    @Size(max = 160, message = "Название района должно быть не длиннее 160 символов")
    private String district;

    @Size(max = 230, message = "Название населённого пункта должно не длиннее 230 символов")
    private String locality;

    @Size(max = 230, message = "Название улицы должно быть не длиннее 230 символов")
    private String street;

    @Min(value = 1, message = "Номер дома должен быть больше 1")
    @Column(name = "house_number")
    private Integer houseNumber;

    @Min(value = 1, message = "Номер блока должен быть больше 1")
    @Column(name = "house_block")
    private Integer houseBlock;

    @Min(value = 1, message = "Номер квартиры должен быть больше 1")
    @Column(name = "flat_number")
    private Integer flatNumber;

    @NotEmpty(message = "Индекс не может быть пустым")
    private Long index;

    @OneToOne(mappedBy = "actualRegistration")
    private Profile profile;
}
