package com.bank.account.Audit;

import com.bank.account.Entity.Audit;
import com.bank.account.Entity.Dto.AccountDetailsDto;
import com.bank.account.Repository.AuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuditListenerTest {

    @InjectMocks
    private AuditListener auditListener;

    @Mock
    private AuditRepository auditRepository;

    private AccountDetailsDto accountDetailsDto;

    @BeforeEach
    public void setUp() {
        accountDetailsDto = new AccountDetailsDto();
        accountDetailsDto.setId(1L);
        accountDetailsDto.setPassportId(12345L);
        accountDetailsDto.setAccountNumber(54321L);
        accountDetailsDto.setBankDetailsId(67890L);
    }

    /**
     * Тест для аудита перед сохранением сущности (операция создания).
     */
    @Test
    public void testPrePersist() {
        auditListener.prePersist(accountDetailsDto);

        verify(auditRepository, times(1)).save(any(Audit.class));
    }

    /**
     * Тест для аудита перед обновлением сущности (операция обновления).
     */
    @Test
    public void testPreUpdate() {
        auditListener.setCurrentAccountDetails(accountDetailsDto);
        AccountDetailsDto updatedAccountDetails = new AccountDetailsDto();
        updatedAccountDetails.setId(1L);
        updatedAccountDetails.setPassportId(12345L);
        updatedAccountDetails.setAccountNumber(54321L);
        updatedAccountDetails.setBankDetailsId(12345L);

        auditListener.preUpdate(updatedAccountDetails);

        verify(auditRepository, times(1)).save(any(Audit.class));
    }

    /**
     * Тест для аудита перед удалением сущности (операция удаления).
     */
    @Test
    public void testPreRemove() {
        auditListener.preRemove(accountDetailsDto);

        verify(auditRepository, times(1)).save(any(Audit.class));
    }
}
