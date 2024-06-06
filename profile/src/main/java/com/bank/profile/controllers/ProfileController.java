package com.bank.profile.controllers;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.Profile;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.service.ActualRegistrationService;
import com.bank.profile.service.PassportService;
import com.bank.profile.service.ProfileService;
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
@Tag(name = "Профили", description = "получаем и редактируем данные по записям профилей")
@RequestMapping("/profile/")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMapper profileMapper;
    private final ActualRegistrationService actualRegistrationService;
    private final PassportService passportService;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    public ProfileController(ProfileService profileService, ProfileMapper profileMapper, ActualRegistrationService actualRegistrationService, PassportService passportService) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
        this.actualRegistrationService = actualRegistrationService;
        this.passportService = passportService;
    }


    @GetMapping("list")
    @Operation(
            summary = "Получение списка записей профилей",
            description = "Позволяет вывести полный список записей профилей"
    )
    public ResponseEntity<List<Profile>> getAllProfiles() {
        logger.info("Запрос списка всех профилей");

        List<Profile> profiles = profileService.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Получение записи профиля по id",
            description = "Позволяет вывести запись профиля по id"
    )
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        logger.info("Запрос паспорта с id {}", id);

        if (!profileService.existById(id)) {
            throw new EntityNotFoundException("Профиля с таким id не существует");
        }

        Profile profile = profileService.findById(id);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("create")
    @Operation(
            summary = "Создание записи профиля",
            description = "Позволяет создать запись профиля. profileDto.id будет присвоено значение null и установлено автоматически по порядку"
    )
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileDto profileDto) {
        logger.info("Запрос на создание профиля");

        Long actualRegistrationId = profileDto.getActualRegistrationId();
        Long passportId = profileDto.getPassportId();

        if (!actualRegistrationService.existById(actualRegistrationId)) {
            throw new EntityNotFoundException("Вы пытаетесь создать профиль с не существующим паспортом");
        }
        if (!passportService.existById(passportId)) {
            throw new EntityNotFoundException("Вы пытаетесь создать профиль с не существующей актуальной регистрацией");
        }

        Profile profile = profileMapper.toEntity(profileDto, passportService, actualRegistrationService);
        profileService.save(profile);

        return ResponseEntity.ok(profile);
    }

    @PatchMapping("update")
    @Operation(
            summary = "Обновление записи профиля",
            description = "Позволяет обновить запись профиля, id редактируемой записи берётся из profileDto"
    )
    public ResponseEntity<Profile> updateProfile(@RequestBody ProfileDto profileDto) throws JsonProcessingException {
        Long id = profileDto.getId();
        Long actualRegistrationId = profileDto.getActualRegistrationId();
        Long passportId = profileDto.getPassportId();

        logger.info("Запрос на обновление профиля с id {}", id);

        if (!profileService.existById(id)) {
            throw new EntityNotFoundException("Профиля с таким id не существует");
        }

        if (!actualRegistrationService.existById(actualRegistrationId)) {
            throw new EntityNotFoundException("Вы пытаетесь создать профиль с не существующей актуальной регистрацией");
        }

        if (!passportService.existById(passportId)) {
            throw new EntityNotFoundException("Вы пытаетесь создать профиль с не существующим паспортом");
        }

        Profile profile = profileMapper.toEntity(profileDto, passportService, actualRegistrationService);
        profileService.update(profile);

        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("delete/{id}")
    @Operation(
            summary = "Удаление записи профиля по id",
            description = "Позволяет удалить запись профиля по id"
    )
    public ResponseEntity<Profile> deleteProfile(@PathVariable Long id) {
        logger.info("Запрос на удаление профиля с id {}", id);

        if (!profileService.existById(id)) {
            throw new EntityNotFoundException("Профиля с таким id не существует");
        }

        Profile profile = profileService.findById(id);
        profileService.delete(profile);

        return ResponseEntity.ok().build();
    }
}
