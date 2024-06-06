package com.bank.account.Controller;

import com.bank.account.Entity.Dto.AccountDetailsDto;
import com.bank.account.Service.AccountDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountDetailsControllerImplTest {

    @Mock
    private AccountDetailsService accountDetailsService;

    @InjectMocks
    private AccountDetailsControllerImpl accountDetailsController;

    private AccountDetailsDto accountDetailsDto;

    @BeforeEach
    public void setUp() {
        accountDetailsDto = new AccountDetailsDto();
        accountDetailsDto.setId(1L);
    }

    /**
     * Тестирует метод получения информации о счете по его ID.
     */
    @Test
    public void testGetAccountDetailsById() {
        when(accountDetailsService.findById(1L)).thenReturn(accountDetailsDto);

        AccountDetailsDto foundAccount = accountDetailsController.getAccountDetailsById(1L);

        assertThat(foundAccount).isEqualTo(accountDetailsDto);
        verify(accountDetailsService, times(1)).findById(1L);
    }

    /**
     * Тестирует метод создания нового счета.
     */
    @Test
    public void testCreateAccount() {
        accountDetailsController.createAccount(accountDetailsDto);

        verify(accountDetailsService, times(1)).createAccount(accountDetailsDto);
    }

    /**
     * Тестирует метод обновления информации о счете.
     */
    @Test
    public void testUpdateAccount() {
        accountDetailsController.updateAccount(1L, accountDetailsDto);

        verify(accountDetailsService, times(1)).updateAccount(accountDetailsDto);
    }

    /**
     * Тестирует метод удаления счета по его ID.
     */
    @Test
    public void testDeleteAccountById() {
        accountDetailsController.deleteAccountById(1L);

        verify(accountDetailsService, times(1)).deleteAccountById(1L);
    }

    /**
     * Тестирует метод получения списка всех счетов.
     */
    @Test
    public void testGetAllAccounts() {
        Set<AccountDetailsDto> accounts = Collections.singleton(accountDetailsDto);
        when(accountDetailsService.getAllAccounts()).thenReturn(accounts);

        Set<AccountDetailsDto> foundAccounts = accountDetailsController.getAllAccounts();

        assertThat(foundAccounts).isEqualTo(accounts);
        verify(accountDetailsService, times(1)).getAllAccounts();
    }
}
