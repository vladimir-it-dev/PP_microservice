package com.bank.account.Entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * Класс AccountDetailsDto представляет собой объект передачи данных (DTO) для счета.
 * Он содержит информацию о владельце счета, номере счета, банковских реквизитах и текущем балансе на счете.
 * <p>
 * Класс содержит следующие аннотации:
 *
 * @Data - заменяет все методы геттеров и сеттеров, а также методы toString, equals и hashCode.
 * @Builder - позволяет использовать паттерн Builder для создания объектов класса.
 * @NoArgsConstructor - добавляет конструктор без аргументов.
 * @AllArgsConstructor - добавляет конструктор со всеми аргументами.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about accountDetails")
public class AccountDetailsDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "идентификатор паспорта владельца счета")
    @NotEmpty(message = "поле не может быть пустым")
    private Long passportId;// идентификатор паспорта владельца счета

    @Schema(description = "Уникальный идентификатор счета")
    @NotEmpty(message = "поле не может быть пустым")
    private Long accountNumber;//уникальный идентификатор счета

    @Schema(description = "Идентификатор банковских реквизитов")
    @NotEmpty(message = "поле не может быть пустым")
    private Long bankDetailsId;//идентификатор банковских реквизитов


    @Schema(description = "Текущий баланс на счете")
    private BigDecimal money;//текущий баланс на счете

    @Schema(description = "Флаг отрицательного баланса на счете")
    private Boolean negativeBalance;//отрицательный баланс

    @Schema(description = "Идентификатор профиля пользователя")
    private Long profileId;
}
