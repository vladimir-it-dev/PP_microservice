package com.bank.antifraud.util;

import com.bank.antifraud.dto.temp.ProfileDetailsDto;
import com.bank.antifraud.dto.temp.TransferDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**

 Реализация интерфейса {@link ServiceClient}, предоставляющая заглушки для всех методов.

 Она должна быть переопределена, когда будут написаны реальные сервисы, которые используются этим кодом.
 */
@Component
public class ServiceClientImpl implements ServiceClient {

    /**
     Проверяет аутентификацию пользователя.
     Возвращает всегда true, пока не будет реализован реальный сервис.
     @param profileId идентификатор профиля пользователя
     @return всегда true
     */
    @Override
    public boolean isUserAuth(long profileId) {
        return true;
    }

    /**
     Получает детали профиля пользователя по его идентификатору.
     Возвращает заглушку {@link ProfileDetailsDto} с произвольными значениями,
     пока не будет реализован реальный сервис.
     @param accountDetailsId идентификатор деталей профиля пользователя
     @return заглушка {@link ProfileDetailsDto}
     */
    @Override
    public ProfileDetailsDto getProfileDetailsById(long accountDetailsId) {
        return ProfileDetailsDto.builder().profileId(1L).accountIds(new ArrayList<>()).transferActualAccount(1L).build();
    }

    /**
     Получает список транзакций пользователя по идентификатору его счета.
     Возвращает пустой список {@link TransferDto}, пока не будет реализован реальный сервис.
     @param accountId идентификатор счета пользователя
     @return пустой список {@link TransferDto}
     */
    @Override
    public List<TransferDto> getTransfersByAccountId(long accountId) {
        return new ArrayList<>();
    }

    /**
     Получает список входящих транзакций по реквизитам (карта/номер/счет).
     Возвращает пустой список {@link TransferDto}, пока не будет реализован реальный сервис.
     @param transferDetails реквизиты транзакций
     @return пустой список {@link TransferDto}
     */
    @Override
    public List<TransferDto> getIncomingTransfersByTransferDetails(long transferDetails) {
        return new ArrayList<>();
    }

    /**
     Проверяет, есть ли у пользователя заблокированные платежи за последние сутки.
     Возвращает всегда false, пока не будет реализован реальный сервис.
     @param profileId идентификатор профиля пользователя
     @return всегда false
     */
    @Override
    public boolean hasBlockedTransfersPerDay(long profileId) {
        return false;
    }
}
