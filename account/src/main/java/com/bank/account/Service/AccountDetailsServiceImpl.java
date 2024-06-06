package com.bank.account.Service;

import com.bank.account.Audit.AuditListener;
import com.bank.account.Entity.AccountDetails;
import com.bank.account.Entity.Dto.AccountDetailsDto;
import com.bank.account.Mapper.AccountDetailsMapper;
import com.bank.account.Repository.AccountDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс AccountDetailsServiceImpl представляет реализацию интерфейса AccountDetailsService,
 * который предоставляет сервис для управления банковскими счетами.
 * <p>
 * Этот класс отмечен
 * аннотацией @Service, что говорит о том, что он является сервисным классом в Spring framework.
 * Его методы обеспечивают поиск, создание, обновление и удаление объектов AccountDetails
 * в БД, а также получение списка всех существующих счетов.
 */
@Service
@Transactional
@AllArgsConstructor
public class AccountDetailsServiceImpl implements AccountDetailsService {
    private AuditListener auditListener;
    private final AccountDetailsRepository accountDetailsRepository;

    @Override
    @Transactional(readOnly = true)
    public AccountDetailsDto findById(Long id) {
        AccountDetails accountDetails = accountDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Номер счета " + id + " не найден"));
        return AccountDetailsMapper.INSTANCE.toDto(accountDetails);

    }

    @Override
    public void createAccount(AccountDetailsDto accountDetailsDto) {
        AccountDetails accountDetails = AccountDetailsMapper.INSTANCE.toEntity(accountDetailsDto);
        accountDetailsRepository.save(accountDetails);
    }

    @Override
    public void updateAccount(AccountDetailsDto accountDetailsDto) {

        AccountDetails accountDetailsExisting = accountDetailsRepository.findById(accountDetailsDto.getId())
                .orElseThrow(() -> new NotFoundException("Аккаунт с ID " + accountDetailsDto.getId() + " не найден"));
        auditListener.setCurrentAccountDetails(AccountDetailsMapper.INSTANCE.toDto(accountDetailsExisting));
        AccountDetails accountDetails = AccountDetailsMapper.INSTANCE.toEntity(accountDetailsDto);
        accountDetailsRepository.save(accountDetails);

    }

    @Override
    public void deleteAccountById(Long id) {
        accountDetailsRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AccountDetailsDto> getAllAccounts() {
        return accountDetailsRepository.findAll().stream()
                .map(AccountDetailsMapper.INSTANCE::toDto)
                .collect(Collectors.toSet());
    }


}
