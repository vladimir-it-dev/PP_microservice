package com.bank.profile.service;

import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.repository.AccountDetailsIdRepository;
import com.bank.profile.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountDetailsIdServiceImpl implements AccountDetailsIdService {

    private final AccountDetailsIdRepository accountDetailsIdRepository;

    public AccountDetailsIdServiceImpl(AccountDetailsIdRepository accountDetailsIdRepository) {
        this.accountDetailsIdRepository = accountDetailsIdRepository;
    }
    @Transactional
    @Override
    public void save(AccountDetailsId accountDetailsId) {
        accountDetailsId.setId(null);
        accountDetailsIdRepository.save(accountDetailsId);
    }

    @Override
    public List<AccountDetailsId> findAll() {
        return accountDetailsIdRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return accountDetailsIdRepository.existsById(id);
    }

    @Override
    public AccountDetailsId findById(Long id) {
        return accountDetailsIdRepository.getReferenceById(id);
    }

    @Transactional
    @Override
    public void update(AccountDetailsId accountDetailsId) throws JsonProcessingException {
        AccountDetailsId unupdatedAccountDetailsId = findById(accountDetailsId.getId());

        accountDetailsId.setCreatedBy(unupdatedAccountDetailsId.getCreatedBy());
        accountDetailsId.setCreatedAt(unupdatedAccountDetailsId.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(unupdatedAccountDetailsId);

        accountDetailsIdRepository.save(accountDetailsId);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        accountDetailsIdRepository.deleteById(id);
    }
}
