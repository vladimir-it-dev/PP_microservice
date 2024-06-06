package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AntiFraudDto;
import com.bank.antifraud.dto.temp.TransferDto;
import com.bank.antifraud.service.CardFraudService;
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
@RequestMapping("/card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Контроллер проверки транзакций на мошенничество (переводы по карте)",
        description = "Проверка транзакций между счетами на возможные мошеннические схемы при переводах по карте")
public class SuspiciousCardTransferController {
    final CardFraudService cardFraudService;
    static final Logger logger = LoggerFactory.getLogger(SuspiciousCardTransferController.class);

    public SuspiciousCardTransferController(CardFraudService cardFraudService) {
        this.cardFraudService = cardFraudService;
    }

    @Operation(summary = "Проверить трансфер",
            description = "Проверяет возможность осуществления трансфера и возвращает результат.")
    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AntiFraudDto> checkTransfer(@RequestBody @Valid TransferDto dto) {
        logger.info("Запрос на проверку CardTransfer {}", dto);
        return ResponseEntity.ok(cardFraudService.check(dto));
    }

    @Operation(summary = "Обновление транзакции", description = "Обновление информации о транзакции")
    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AntiFraudDto> updateTransfer(@RequestBody @Valid AntiFraudDto dto, @PathVariable("id") long id) {
        logger.info("Запрос на апдейт CardTransfer {}", dto);
        dto.setId(id);
        return ResponseEntity.ok(cardFraudService.update(dto));
    }

    @Operation(summary = "Удаление результата проверки по ID",
            description = "Метод удаляет результат из базы данных по указанному ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransfer(@PathVariable("id") @Positive long id) {
        logger.info("Запрос на удаление CardTransfer {}", id);
        cardFraudService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Получить результат проверки перевода по ID",
            description = "Возвращает информацию о проверке по заданному ID")
    @GetMapping("/{id}")
    public ResponseEntity<AntiFraudDto> getById(@PathVariable("id") @Positive long id){
        logger.info("Запрос на получение CardTransfer {}", id);
        return ResponseEntity.ok(cardFraudService.getById(id));
    }
}
