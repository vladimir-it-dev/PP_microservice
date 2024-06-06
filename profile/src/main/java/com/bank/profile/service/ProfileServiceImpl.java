package com.bank.profile.service;

import com.bank.profile.entity.Profile;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;


    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void save(Profile profile) {
        profile.setId(null);
        profileRepository.save(profile);
    }

    @Transactional
    @Override
    public void update(Profile profile) throws JsonProcessingException {
        Profile unupdatedProfile = findById(profile.getId());

        profile.setCreatedBy(unupdatedProfile.getCreatedBy());
        profile.setCreatedAt(unupdatedProfile.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(unupdatedProfile);

        profileRepository.save(profile);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile findById(Long id) {
        return profileRepository.getReferenceById(id);
    }

    @Override
    public boolean existById(Long id) {
        return profileRepository.existsById(id);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        profileRepository.delete(findById(id));
    }

    @Transactional
    @Override
    public void delete(Profile profile) {
        profileRepository.delete(profile);
    }
}
