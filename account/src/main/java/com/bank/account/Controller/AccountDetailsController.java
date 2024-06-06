package com.bank.account.Controller;

import com.bank.account.Entity.Dto.AccountDetailsDto;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Контроллер для работы с сущностями AccountDetails через API REST.
 */
@Tag(name = "AccountDetailsController", description = "REST API для работы со счетами")
@RestController
@RequestMapping("/accounts")
public interface AccountDetailsController {

    /**
     * Получает информацию о счете по его номеру.
     *
     * @param id клиента
     * @return DTO AccountDetailsDto
     */

    @Operation(summary = "Получает информацию о счете по его номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "201", description = "Ресурс успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос. Проверьте параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Счет с указанным id не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера. Обратитесь в техническую поддержку")
    })
    @GetMapping("/{id}")
    AccountDetailsDto getAccountDetailsById(@ApiParam(value = "ID счета", required = true) @PathVariable Long id);

    /**
     * Создает новый счет.
     *
     * @param accountDetailsDto DTO AccountDetailsDto
     */
    @Operation(summary = "Создает новый счет")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "201", description = "Ресурс успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос. Проверьте параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Счет с указанным id не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера. Обратитесь в техническую поддержку")
    })
    @PostMapping
    void createAccount(@ApiParam(value = "DTO счета", required = true) @RequestBody AccountDetailsDto accountDetailsDto);

    /**
     * Обновляет информацию о счете
     *
     * @param accountDetailsDto DTO AccountDetailsDto
     */

    @Operation(summary = "Обновляет информацию о счете")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "201", description = "Ресурс успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос. Проверьте параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Счет с указанным id не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера. Обратитесь в техническую поддержку")
    })
    @PatchMapping("/{id}")
    void updateAccount(@ApiParam(value = "ID счета", required = true) @PathVariable Long id, @ApiParam(value = "DTO счета", required = true) @RequestBody AccountDetailsDto accountDetailsDto);

    /**
     * Удаляет счет по его id.
     *
     * @param id id счета
     */
    @Operation(summary = "Удаляет счет по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "201", description = "Ресурс успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос. Проверьте параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Счет с указанным id не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера. Обратитесь в техническую поддержку")
    })
    @DeleteMapping("/{id}")
    void deleteAccountById(@ApiParam(value = "ID счета", required = true) @PathVariable Long id);

    /**
     * Получает список всех счетов.
     *
     * @return множество DTO AccountDetailsDto
     */
    @Operation(summary = "Получает список всех счетов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "201", description = "Ресурс успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос. Проверьте параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Счет с указанным id не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера. Обратитесь в техническую поддержку")
    })
    @GetMapping
    Set<AccountDetailsDto> getAllAccounts();
}
