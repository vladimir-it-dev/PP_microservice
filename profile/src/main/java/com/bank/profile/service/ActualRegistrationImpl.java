package com.bank.profile.service;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActualRegistrationImpl implements ActualRegistrationService {
    private final ActualRegistrationRepository actualRegistrationRepository;

    public ActualRegistrationImpl(ActualRegistrationRepository actualRegistrationRepository) {
        this.actualRegistrationRepository = actualRegistrationRepository;
    }

    @Transactional
    @Override
    public void save(ActualRegistration actualRegistration) {
        actualRegistration.setId(null);
        actualRegistrationRepository.save(actualRegistration);
    }

    @Override
    public List<ActualRegistration> findAll() {
        return actualRegistrationRepository.findAll();
    }

    @Override
    public ActualRegistration findById(Long id) {
        return actualRegistrationRepository.getReferenceById(id);
    }

    @Transactional
    @Override
    public void update(ActualRegistration actualRegistration) throws JsonProcessingException {
        ActualRegistration unupdatedActualRegistration = findById(actualRegistration.getId());

        actualRegistration.setCreatedBy(unupdatedActualRegistration.getCreatedBy());
        actualRegistration.setCreatedAt(unupdatedActualRegistration.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(unupdatedActualRegistration);

        actualRegistrationRepository.save(actualRegistration);
    }

    @Override
    public boolean existById(Long id) {
        return actualRegistrationRepository.existsById(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        actualRegistrationRepository.deleteById(id);
    }
}
