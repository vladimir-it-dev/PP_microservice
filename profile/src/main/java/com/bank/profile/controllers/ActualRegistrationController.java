package com.bank.profile.controllers;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.mapper.ActualRegistrationMapper;
import com.bank.profile.service.ActualRegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Tag(name = "Актуальные регистрации", description = "получаем и редактируем данные по записям актуальной регистрации")
@RequestMapping("/actual_registration/")
public class ActualRegistrationController {
    private final ActualRegistrationService actualRegistrationService;
    private final ActualRegistrationMapper actualRegistrationMapper;
    private static final Logger logger = LoggerFactory.getLogger(ActualRegistrationController.class);

    public ActualRegistrationController(ActualRegistrationService actualRegistrationService, ActualRegistrationMapper actualRegistrationMapper) {
        this.actualRegistrationService = actualRegistrationService;
        this.actualRegistrationMapper = actualRegistrationMapper;
    }

    @GetMapping("list")
    @Operation(
            summary = "Получение списка записей актуальной регистрации",
            description = "Позволяет вывести полный список записей актуальной регистрации"
    )
    public ResponseEntity<List<ActualRegistration>> getAllActualRegistrations() {
        logger.info("Запрос списка всех актуальных регистраций");

        List<ActualRegistration> actualRegistrations = actualRegistrationService.findAll();

        return ResponseEntity.ok(actualRegistrations);
    }

    @PostMapping("create")
    @Operation(
            summary = "Создание новой записи актуальной регистрации",
            description = "Позволяет создать новую запись актуальной регистрации. actualRegistrationDto.id будет " +
                    "присвоено значение null и установлено автоматически по порядку"
    )
    public ResponseEntity<ActualRegistration> createActualRegistration(@RequestBody ActualRegistrationDto actualRegistrationDto){
        logger.info("Создание новой актуальной регистрации");

        ActualRegistration actualRegistration = actualRegistrationMapper.toEntity(actualRegistrationDto);
        actualRegistrationService.save(actualRegistration);

        return ResponseEntity.ok(actualRegistration);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Получение записи актуальной регистрации",
            description = "Позволяет вывести полный список записей актуальной регистрации по id"
    )
    public ResponseEntity<ActualRegistration> getActualRegistrationById(@PathVariable long id) {
        logger.info("Запрос актуальной регистрации с id {}", id);

        if (!actualRegistrationService.existById(id)) {
            throw new EntityNotFoundException("Актуальной регистрации с таким id не существует");
        }
        ActualRegistration actualRegistration = actualRegistrationService.findById(id);
        return ResponseEntity.ok(actualRegistration);
    }

    @PatchMapping("update")
    @Operation(
            summary = "Обновление записи актуальной регистрации",
            description = "Позволяет обновить запись актуальной регистрации, id редактируемой записи берётся из actualRegistrationDto"
    )
    public ResponseEntity<ActualRegistration> updateActualRegistrationById(@RequestBody ActualRegistrationDto actualRegistrationDto) throws JsonProcessingException {
        Long id = actualRegistrationDto.getId();

        logger.info("Запрос на редактирование актуальной регистрации с id {}", id);

        if (!actualRegistrationService.existById(id)) {
            throw new EntityNotFoundException("Регистрации с таким id не существует");
        }

        ActualRegistration actualRegistration = actualRegistrationMapper.toEntity(actualRegistrationDto);
        actualRegistrationService.update(actualRegistration);

        return ResponseEntity.ok(actualRegistration);
    }

    @DeleteMapping("delete/{id}")
    @Operation(
            summary = "Удаление записи актуальной регистрации",
            description = "Позволяет удалить запись актуальной регистрации по id"
    )
    public ResponseEntity<ActualRegistration> deleteUser(@PathVariable Long id) {
        logger.info("Запрос на удаление актуальной регистрации с id {}", id);

        if (!actualRegistrationService.existById(id)) {
            throw new EntityNotFoundException("актуальной регистрации с таким id не существует");
        }

        actualRegistrationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
