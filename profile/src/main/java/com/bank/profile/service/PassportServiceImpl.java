package com.bank.profile.service;

import com.bank.profile.entity.Passport;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final RegistrationMapper registrationMapper;
    private final PassportMapper passportMapper;
    private final RegistrationService registrationService;

    public PassportServiceImpl(PassportRepository passportRepository, RegistrationMapper registrationMapper, PassportMapper passportMapper, RegistrationService registrationService) {
        this.passportRepository = passportRepository;
        this.registrationMapper = registrationMapper;
        this.passportMapper = passportMapper;
        this.registrationService = registrationService;
    }

    @Transactional
    @Override
    public void save(Passport passport) {
        passport.setId(null);
        passportRepository.save(passport);
    }

    @Override
    public List<Passport> findAll() {
        return passportRepository.findAll();
    }

    @Override
    public Passport findById(Long id) {
        return passportRepository.getReferenceById(id);
    }

    @Transactional
    @Override
    public void update(Passport passport) throws JsonProcessingException {
        Passport unupdatedPassport = findById(passport.getId());

        passport.setCreatedBy(unupdatedPassport.getCreatedBy());
        passport.setCreatedAt(unupdatedPassport.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(unupdatedPassport);

        passportRepository.save(passport);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        passportRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return passportRepository.existsById(id);
    }
}
