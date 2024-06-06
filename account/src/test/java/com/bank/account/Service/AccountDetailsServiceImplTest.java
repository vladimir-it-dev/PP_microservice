package com.bank.account.Service;

import com.bank.account.Audit.AuditListener;
import com.bank.account.Entity.AccountDetails;
import com.bank.account.Entity.Dto.AccountDetailsDto;
import com.bank.account.Mapper.AccountDetailsMapper;
import com.bank.account.Repository.AccountDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountDetailsServiceImplTest {

    @Mock
    private AccountDetailsRepository accountDetailsRepository;

    @Mock
    private AuditListener auditListener;

    @InjectMocks
    private AccountDetailsServiceImpl accountDetailsService;

    private AccountDetails accountDetails;
    private AccountDetailsDto accountDetailsDto;

    @BeforeEach
    public void setUp() {
        accountDetails = AccountDetails.builder()
                .id(1L)
                .passportId(123456L)
                .accountNumber(1234567890L)
                .bankDetailsId(1L)
                .money(BigDecimal.valueOf(1000))
                .negativeBalance(false)
                .profileId(1L)
                .build();

        accountDetailsDto = AccountDetailsMapper.INSTANCE.toDto(accountDetails);
    }

    /**
     * Тест поиска счета по идентификатору.
     */
    @Test
    public void testFindById() {
        when(accountDetailsRepository.findById(1L)).thenReturn(Optional.of(accountDetails));

        AccountDetailsDto result = accountDetailsService.findById(1L);
        assertEquals(accountDetailsDto, result);

        verify(accountDetailsRepository).findById(1L);
    }

    /**
     * Тест создания счета.
     */
    @Test
    public void testCreateAccount() {
        when(accountDetailsRepository.save(any(AccountDetails.class))).thenReturn(accountDetails);

        accountDetailsService.createAccount(accountDetailsDto);

        verify(accountDetailsRepository).save(any(AccountDetails.class));
    }

    /**
     * Тест обновления счета.
     */
    @Test
    public void testUpdateAccount() {
        AccountDetails accountDetails = AccountDetailsMapper.INSTANCE.toEntity(accountDetailsDto);
        when(accountDetailsRepository.findById(accountDetailsDto.getId())).thenReturn(Optional.of(accountDetails));

        accountDetailsService.updateAccount(accountDetailsDto);

        verify(auditListener, times(1)).setCurrentAccountDetails(any(AccountDetailsDto.class));
        verify(accountDetailsRepository, times(1)).save(any(AccountDetails.class));
    }


    /**
     * Тест удаления счета по идентификатору.
     */
    @Test
    public void testDeleteAccountById() {
        doNothing().when(accountDetailsRepository).deleteById(1L);

        accountDetailsService.deleteAccountById(1L);

        verify(accountDetailsRepository).deleteById(1L);
    }

    /**
     * Тест получения списка всех счетов.
     */
    @Test
    public void testGetAllAccounts() {
        when(accountDetailsRepository.findAll()).thenReturn(Collections.singletonList(accountDetails));

        Set<AccountDetailsDto> expected = new HashSet<>();
        expected.add(accountDetailsDto);
        Set<AccountDetailsDto> result = accountDetailsService.getAllAccounts();

        assertEquals(expected, result);
        verify(accountDetailsRepository).findAll();
    }
}
