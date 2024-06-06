package com.bank.profile.controllers;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.Passport;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.service.PassportService;
import com.bank.profile.service.RegistrationService;
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
@Tag(name = "Паспорта", description = "получаем и редактируем данные по записям паспортов")
@RequestMapping("/passport/")
public class PassportController {

    private final PassportService passportService;
    private final PassportMapper passportMapper;
    private final RegistrationService registrationService;
    private final RegistrationMapper registrationMapper;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    public PassportController(PassportService passportService, PassportMapper passportMapper, RegistrationMapper registrationMapper, RegistrationService registrationService, RegistrationMapper registrationMapper1) {
        this.passportService = passportService;
        this.passportMapper = passportMapper;
        this.registrationService = registrationService;
        this.registrationMapper = registrationMapper1;
    }

    @GetMapping("list")
    @Operation(
            summary = "Получение списка записей паспортов",
            description = "Позволяет вывести полный список записей паспортов"
    )
    public ResponseEntity<List<Passport>> getAllPassports() {
        logger.info("Запрос списка всех паспортов");
        List<Passport> passports = passportService.findAll();

        return ResponseEntity.ok(passports);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Получение записи паспорта по id",
            description = "Позволяет вывести запись паспорта по id"
    )
    public ResponseEntity<Passport> getPassportById(@PathVariable long id) {
        logger.info("Запрос паспорта с id {}", id);

        if (!passportService.existById(id)) {
            throw new EntityNotFoundException("Паспорта с таким id не существует");
        }

        Passport passport = passportService.findById(id);

        return ResponseEntity.ok(passport);
    }

    @PostMapping("create")
    @GetMapping("{id}")
    @Operation(
            summary = "Создание записи паспорта",
            description = "Позволяет создать запись паспорта. passportDto.id будет присвоено значение null и установлено автоматически по порядку"
    )
    public ResponseEntity<Passport> createPassport(@RequestBody PassportDto passportDto) {
        logger.info("Запрос на создание паспорта");

        Passport passport = passportMapper.toEntity(passportDto, registrationService);
        Long registrationId = passportDto.getRegistrationId();

        if (!registrationService.existById(registrationId)) {
            throw new EntityNotFoundException("Вы пытаетесь создать паспорт с не существующей регистрацией");
        }

        passport.setRegistration(registrationService.findById(registrationId));
        passportService.save(passport);

        return ResponseEntity.ok(passport);
    }

    @PatchMapping("update")
    @Operation(
            summary = "Обновление записи паспорта",
            description = "Позволяет обновить запись паспорта, id редактируемой записи берётся из passportDto"
    )
    public ResponseEntity<Passport> updatePassport(@RequestBody PassportDto passportDto) throws JsonProcessingException {
        Long id = passportDto.getId();
        Long registrationId = passportDto.getRegistrationId();

        logger.info("Запрос на обновление паспорта с id {}", id);

        if (!passportService.existById(id)) {
            throw new EntityNotFoundException("Паспорта с таким id не существует");
        }

        if (!registrationService.existById(registrationId)) {
            throw new EntityNotFoundException("Вы пытаетесь создать паспорт с не существующей регистрацией");
        }

        Passport passport = passportMapper.toEntity(passportDto, registrationService);
        passportService.update(passport);

        return ResponseEntity.ok(passport);
    }

    @DeleteMapping("delete/{id}")
    @Operation(
            summary = "Удаление записи паспорта по id",
            description = "Позволяет удалить запись паспорта по id"
    )
    public ResponseEntity<Passport> deleteUser(@PathVariable Long id) {
        logger.info("Запрос на удаление паспорта с id {}", id);

        if (!passportService.existById(id)) {
            throw new EntityNotFoundException("Паспорта с таким id не существует");
        }

        passportService.delete(id);
        return ResponseEntity.ok().build();
    }
}
