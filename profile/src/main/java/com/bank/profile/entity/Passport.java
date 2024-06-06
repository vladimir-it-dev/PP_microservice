package com.bank.profile.entity;

import com.bank.profile.serializer.PassportSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 *Класс Passport представляет собой информацию о паспорте,
 * которая сохраняется в таблице passport.
 * Passport связан отношением One-to-one с сущностями Profile и Registration, может иметь пустое
 * значение поля profile.
 * Для сериализации спользуется PassportSerializer
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = PassportSerializer.class)
@Table(name = "passport")
public class Passport extends BaseEntity {

    @NotEmpty(message = "Серия не может быть пустой")
    private Integer series;

    @NotEmpty(message = "Номер не может быть пустым")
    private Long number;

    @Size(max = 255, message = "Фамилия должна быть не длиннее 255 символов")
    @NotEmpty(message = "Фамилия не может быть пустой")
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 255, message = "Имя должно быть не длиннее 255 символов")
    @NotEmpty(message = "Имя не может быть пустым")
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255, message = "Отчество должно быть не длиннее 255 символов")
    @Column(name = "middle_name")
    private String middleName;

    @NotEmpty(message = "Пол не может быть пустым")
    @Pattern(regexp = "^(ЖЕН|МУЖ)$", message = "Пол может быть только 'ЖЕН' или 'МУЖ'")
    private String gender;

    @NotEmpty(message = "Дата рождения не может быть пустой")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotEmpty(message = "Место рождения не может быть пустым")
    @Size(max = 480, message = "Место рождения должно быть не длиннее 480 символов")
    @Column(name = "birth_place")
    private String birthPlace;

    @NotEmpty(message = "Выдавший паспорт орган не может быть пустым")
    @Column(name = "issued_by")
    private String issuedBy;

    @NotEmpty(message = "Дата выдачи не может быть пустой")
    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @NotEmpty(message = "Код подразделения не может быть пустым")
    @Column(name = "division_code")
    private Integer divisionCode;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @NotEmpty(message = "Регистрация не может быть пустой")
    @OneToOne
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
    private Registration registration;

    @OneToOne(mappedBy = "passport")
    private Profile profile;
}
