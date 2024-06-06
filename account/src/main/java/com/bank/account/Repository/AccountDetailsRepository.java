package com.bank.account.Repository;

import com.bank.account.Entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Интерфейс AccountDetailsRepository наследуется от JpaRepository и
 * предоставляет доступ к хранилищу данных для управления банковскими счетами.
 */
@Component
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {

}
