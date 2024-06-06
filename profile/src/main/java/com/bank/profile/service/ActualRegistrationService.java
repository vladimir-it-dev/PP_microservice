package com.bank.profile.service;

import com.bank.profile.entity.ActualRegistration;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ActualRegistrationService {
    void save(ActualRegistration actualRegistration);
    List<ActualRegistration> findAll();
    ActualRegistration findById(Long id);
    void update(ActualRegistration actualRegistration) throws JsonProcessingException;
    void delete(Long id);
    boolean existById(Long id);
}
