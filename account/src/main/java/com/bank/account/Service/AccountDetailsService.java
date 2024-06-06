package com.bank.account.Service;

import com.bank.account.Entity.Dto.AccountDetailsDto;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Интерфейс AccountDetailsService предоставляет сервис для управления банковскими счетами.
 * Он включает методы для поиска, создания, обновления и удаления информации о счетах,
 * а также для получения списка всех существующих счетов.
 */
@Service
public interface AccountDetailsService {

    AccountDetailsDto findById(Long id);//Поиск объекта по номеру счета

    void createAccount(AccountDetailsDto accountDetailsDto);//создание нового объекта AccountDetails в БД

    void updateAccount(AccountDetailsDto accountDetailsDto);//обновление объекта AccountDetails в БД

    void deleteAccountById(Long id);// удаление объекта AccountDetails по Id из БД

    Set<AccountDetailsDto> getAllAccounts();//получение списка всех объектов AccountDetails из БД


}
