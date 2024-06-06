package com.bank.profile.service;

import com.bank.profile.entity.Registration;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface RegistrationService {
    void save(Registration registration);
    List<Registration> findAll();
    Registration findById(Long id);
    void delete(Long id);
    void update(Registration registration) throws JsonProcessingException;
    boolean existById(Long id);
}
