package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AntiFraudDto;
import com.bank.antifraud.dto.temp.TransferDto;
import com.bank.antifraud.service.PhoneFraudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/phone")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Контроллер проверки транзакций на мошенничество (переводы по номеру телефона)",
        description = "Проверка транзакций между счетами на возможные мошеннические схемы при переводах по номеру телефона")
public class SuspiciousPhoneTransferController {

    final PhoneFraudService phoneFraudService;
    static final Logger logger = LoggerFactory.getLogger(SuspiciousPhoneTransferController.class);

    public SuspiciousPhoneTransferController(PhoneFraudService phoneFraudService) {
        this.phoneFraudService = phoneFraudService;
    }

    @Operation(summary = "Проверить трансфер",
            description = "Проверяет возможность осуществления трансфера и возвращает результат.")
    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AntiFraudDto> checkTransfer(@RequestBody @Valid TransferDto dto) {
        logger.info("Запрос на проверку PhoneTransfer {}", dto);
        return ResponseEntity.ok(phoneFraudService.check(dto));
    }

    @Operation(summary = "Обновление транзакции", description = "Обновление информации о транзакции")
    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AntiFraudDto> updateTransfer(@RequestBody @Valid AntiFraudDto dto, @PathVariable("id") long id) {
        logger.info("Запрос на апдейт PhoneTransfer {}", dto);
        dto.setId(id);
        return ResponseEntity.ok(phoneFraudService.update(dto));
    }

    @Operation(summary = "Удаление результата проверки по ID",
            description = "Метод удаляет результат из базы данных по указанному ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransfer(@PathVariable("id") @Positive long id) {
        logger.info("Запрос на удаление PhoneTransfer {}", id);
        phoneFraudService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Получить результат проверки перевода по ID",
            description = "Возвращает информацию о проверке по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<AntiFraudDto> getById(@PathVariable("id") @Positive long id){
        logger.info("Запрос на получение PhoneTransfer {}", id);
        return ResponseEntity.ok(phoneFraudService.getById(id));
    }
}
