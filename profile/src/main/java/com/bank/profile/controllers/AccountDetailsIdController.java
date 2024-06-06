package com.bank.profile.controllers;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.mapper.AccountDetailsIdMapper;
import com.bank.profile.service.AccountDetailsIdService;
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
@Tag(name = "Счета", description = "получаем и редактируем данные по записям счетов")
@RequestMapping("/account_details_id/")
public class AccountDetailsIdController {
    private final AccountDetailsIdService accountDetailsIdService;
    private final AccountDetailsIdMapper accountDetailsIdMapper;
    private final ProfileService profileService;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    public AccountDetailsIdController(AccountDetailsIdService accountDetailsIdService, AccountDetailsIdMapper accountDetailsIdMapper, ProfileService profileService) {
        this.accountDetailsIdService = accountDetailsIdService;
        this.accountDetailsIdMapper = accountDetailsIdMapper;
        this.profileService = profileService;
    }

    @GetMapping("list")
    @Operation(
            summary = "Получение списка записей счетов",
            description = "Позволяет вывести полный список записей счетов"
    )
    public ResponseEntity<List<AccountDetailsId>> getAllAccountDetailsId() {
        logger.info("Запрос списка всех счетов");
        List<AccountDetailsId> accountDetailsIdList = accountDetailsIdService.findAll();
        return ResponseEntity.ok(accountDetailsIdList);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Получение записи счёта по id",
            description = "Позволяет вывести запись счёта по id"
    )
    public ResponseEntity<AccountDetailsId> getAccountDetailsIdById(@PathVariable Long id) {
        logger.info("Запрос счёта с id {}", id);

        if (!accountDetailsIdService.existById(id)) {
            throw new EntityNotFoundException("Счёта с таким id не существует");
        }

        AccountDetailsId accountDetailsId = accountDetailsIdService.findById(id);

        return ResponseEntity.ok(accountDetailsId);
    }

    @PostMapping("create")
    @Operation(
            summary = "Создание записи счёта",
            description = "Позволяет создать запись счёта. accountDetailsIdDto.id будет присвоено значение null и установлено автоматически по порядку"
    )
    public ResponseEntity<AccountDetailsId> createAccountDetailsId(@RequestBody AccountDetailsIdDto accountDetailsIdDto) {
        logger.info("Запрос на создание нового счёта");
        Long profileId = accountDetailsIdDto.getProfileId();

        if (!profileService.existById(profileId)) {
            throw new EntityNotFoundException("Профиля с таким id не существует");
        }

        AccountDetailsId accountDetailsId = accountDetailsIdMapper.toEntity(accountDetailsIdDto, profileService);
        accountDetailsIdService.save(accountDetailsId);

        return ResponseEntity.ok(accountDetailsId);
    }

    @PatchMapping("update")
    @Operation(
            summary = "Обновление записи счёта",
            description = "Позволяет обновить запись счёта, id редактируемой записи берётся из accountDetailsIdDto"
    )
    public ResponseEntity<AccountDetailsId> updateAccountDetailsId(@RequestBody AccountDetailsIdDto accountDetailsIdDto) throws JsonProcessingException {
        logger.info("Запрос на обновление счёта");
        Long profileId = accountDetailsIdDto.getProfileId();
        Long id = accountDetailsIdDto.getId();

        if (!accountDetailsIdService.existById(id)) {
            throw new EntityNotFoundException("Счёта с таким id не существует");
        }
        if (!profileService.existById(profileId)) {
            throw new EntityNotFoundException("Профиля с таким id не существует");
        }

        AccountDetailsId accountDetailsId = accountDetailsIdMapper.toEntity(accountDetailsIdDto, profileService);
        accountDetailsIdService.update(accountDetailsId);

        return ResponseEntity.ok(accountDetailsId);
    }

    @DeleteMapping("delete/{id}")
    @Operation(
            summary = "Удаление записи счёта по id",
            description = "Позволяет удалить запись счёта по id"
    )
    public ResponseEntity<AccountDetailsId> deleteAccountDetailsIdById(@PathVariable Long id) {
        logger.info("Запрос на удаление счёта с id {}", id);

        if (!accountDetailsIdService.existById(id)) {
            throw new EntityNotFoundException("Счёта с таким id не существует");
        }

        accountDetailsIdService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
