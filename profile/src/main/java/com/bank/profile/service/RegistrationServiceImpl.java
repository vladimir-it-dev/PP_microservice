package com.bank.profile.service;

import com.bank.profile.entity.Registration;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Transactional
    @Override
    public void save(Registration registration) {
        registration.setId(null);
        registrationRepository.save(registration);
    }

    @Override
    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration findById(Long id) {
        return registrationRepository.getReferenceById(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        registrationRepository.delete(findById(id));
    }

    @Transactional
    @Override
    public void update(Registration registration) throws JsonProcessingException {
        Registration unupdatedRegistration = findById(registration.getId());

        registration.setCreatedBy(unupdatedRegistration.getCreatedBy());
        registration.setCreatedAt(unupdatedRegistration.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(unupdatedRegistration);

        registrationRepository.save(registration);
    }

    @Override
    public boolean existById(Long id) {
        return registrationRepository.existsById(id);
    }
}
