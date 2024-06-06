package com.bank.profile.service;

import com.bank.profile.entity.AccountDetailsId;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AccountDetailsIdService {
    void save(AccountDetailsId accountDetailsId);
    List<AccountDetailsId> findAll();
    boolean existById(Long id);
    AccountDetailsId findById(Long id);
    void update(AccountDetailsId accountDetailsId) throws JsonProcessingException;
    void deleteById(Long id);
}
