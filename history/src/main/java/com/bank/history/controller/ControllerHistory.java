package com.bank.history.controller;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.entity.History;
import com.bank.history.exception.HistoryNotFoundException;
import com.bank.history.handler.HistoryErrorResponse;
import com.bank.history.service.ServiceHistory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ControllerHistory предназначен для получения и отправки данных клиенту.
 */
@RestController
@Slf4j
@RequestMapping()
@Tag(name = "История аудита", description = "получение истории операций")
public class ControllerHistory {
    private final ServiceHistory serviceHistory;

    @Autowired
    public ControllerHistory(ServiceHistory serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    @GetMapping()
    @Operation(summary = "Получение всей истории аудита из базы данных", description = "Получение всей истории аудита из базы данных")
    public ResponseEntity<List<HistoryDTO>> allHistory() {
        log.info("Запрос на получение всей истории аудита из базы данных.");
        List<HistoryDTO> allListHistory = serviceHistory.findAllHistory();
        return allListHistory != null && !allListHistory.isEmpty()
                ? new ResponseEntity<>(allListHistory, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение истории аудита из базы данных по id", description = "Получение истории аудита из базы данных по id")
    public ResponseEntity<HistoryDTO> getByIdHistory(@PathVariable("id") Long id) {
        log.info("Запрос на получение истории аудита из базы данных по id = {}.", id);
        return new ResponseEntity<>(serviceHistory.findByIdHistory(id), HttpStatus.OK);
    }

    @PostMapping()
    @Operation(summary = "Добавление истории аудита в базу данных", description = "Добавление истории аудита в базу данных")
    public ResponseEntity<History> saveHistory(@RequestBody HistoryDTO historyDTO) {
        log.info("Запрос на добавление истории аудита в базу данных.");
        History history = serviceHistory.saveHistory(historyDTO);
        return new ResponseEntity<>(history, HttpStatus.CREATED);
    }

    @PutMapping()
    @Operation(summary = "Обновление истории аудита в базе данных", description = "Обновление истории аудита в базе данных")
    public ResponseEntity<History> updateHistory(@RequestBody HistoryDTO historyDTO) {
        log.info("Запрос на обновление истории аудита в базе данных.");
        History history = serviceHistory.updateHistory(historyDTO);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление истории аудита в базе данных", description = "Удаление истории аудита в базе данных")
    public ResponseEntity<HistoryDTO> deleteHistory(@PathVariable("id") Long id) {
        log.info("Запрос на удаление истории аудита в базе данных.");
        serviceHistory.deleteHistory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Данный метод перехватывает исключение HistoryNotFoundException и возвращает сообщение клиенту в виде JSON объекта.
     */
    @ExceptionHandler
    public ResponseEntity<HistoryErrorResponse> handleException(HistoryNotFoundException e) {
        log.error("ERROR: История аудита с этим ID не найдена.");
        HistoryErrorResponse response = new HistoryErrorResponse(
                "История аудита с этим ID не найдена.",
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
