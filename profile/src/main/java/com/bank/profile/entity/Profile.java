package com.bank.profile.entity;

import com.bank.profile.serializer.ProfileSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *Класс Profile представляет собой информацию о банковском профиле,
 * которая сохраняется в таблице profile.
 * Passport связан отношением One-to-one с сущностями Passport и ActualRegistration, a также
 * One-to-many с сущностью AccountDetailsId, поля  actualRegistration и accounts могут быть пустыми. При удалении экземпляра
 * Profile каскадно удаляются связанные с ним экземпляры AccountDetailsId.
 * Для сериализации спользуется ProfileSerializer
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = ProfileSerializer.class)
@Table(name = "profile")
public class Profile extends BaseEntity {

    @NotEmpty(message = "Серия не может быть пустой")
    @Column(name = "phone_number")
    private Long phoneNumber;

    @Email(message = "Email должен быть валидным")
    private String email;

    @Size(max = 370, message = "Имя на карте должно быть не длиннее 370 символов")
    @Column(name = "name_on_card")
    private String nameOnCard;

    private Long inn;

    private Long snils;

    @OneToMany(mappedBy = "owner")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<AccountDetailsId> accounts;

    @NotEmpty(message = "Паспорт не может быть пустым")
    @OneToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @NotEmpty(message = "Актуальная регистрация не может быть пустой")
    @OneToOne
    @JoinColumn(name = "actual_registration_id", referencedColumnName = "id")
    private ActualRegistration actualRegistration;
}
