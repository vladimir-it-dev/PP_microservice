package com.bank.antifraud.util;


import com.bank.antifraud.dto.temp.ProfileDetailsDto;
import com.bank.antifraud.dto.temp.TransferDto;

import java.util.List;

/**

 Интерфейс, предоставляющий методы для работы с клиентскими данными.
 */
public interface ServiceClient {

    /**
     Проверяет аутентификацию пользователя по его ID.
     @param profileId ID пользователя
     @return true, если пользователь аутентифицирован, false - в противном случае
     */
    boolean isUserAuth(long profileId);

    /**
     Получает информацию о счетах пользователя по ID.
     @param accountDetailsId ID счета пользователя
     @return DTO с информацией о счетах пользователя
     */
    ProfileDetailsDto getProfileDetailsById(long accountDetailsId);


    /**
     Получает список платежей пользователя из сервиса переводов.
     Может использоваться, например, для получения среднего чека пользователя,
     информации о первой транзакции, использовании указанного счета и т.д.
     @param accountId ID счета пользователя
     @return список DTO с информацией о платежах пользователя
     */
    List<TransferDto> getTransfersByAccountId(long accountId);

    /**
     Получает список входящих переводов по указанным реквизитам (карта/номер/счет).
     @param transferDetails реквизиты перевода
     @return список DTO с информацией о переводах
     */
    List<TransferDto> getIncomingTransfersByTransferDetails(long transferDetails);

    /**
     Проверяет, есть ли у клиента заблокированные платежи за последние сутки.
     @param profileId ID профиля пользователя
     @return true, если есть заблокированные платежи, false - в противном случае
     */
    boolean hasBlockedTransfersPerDay(long profileId);
}
