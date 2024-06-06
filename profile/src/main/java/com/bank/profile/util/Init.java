package com.bank.profile.util;

import com.bank.profile.entity.*;
import com.bank.profile.mapper.*;
import com.bank.profile.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.time.LocalDate;

@Component
public class Init {
    private final RegistrationService registrationService;
    private final PassportService passportService;
    private final ActualRegistrationService actualRegistrationService;
    private final ProfileService profileService;
    private final AccountDetailsIdService accountDetailsIdService;
    private final AuditService auditService;
    private final ProfileMapper profileMapper;

/**
 * Init при запуске модуля создаёт для примера экземпляры сущностей и сохраняет их в базу данных
 * */
    public Init(RegistrationService registrationService, PassportService passportService, ActualRegistrationService actualRegistrationService, ProfileService profileService, AccountDetailsIdService accountDetailsIdService, AuditService auditService, ProfileMapper profileMapper) {
        this.registrationService = registrationService;
        this.passportService = passportService;
        this.actualRegistrationService = actualRegistrationService;
        this.profileService = profileService;
        this.accountDetailsIdService = accountDetailsIdService;
        this.auditService = auditService;

        this.profileMapper = profileMapper;
    }

    @PostConstruct
    public void initialization() throws ParseException {

        Registration registration = new Registration();
        registration.setCountry("Russia");
        registration.setRegion("Europa");
        registration.setCity("Moscow");
        registration.setDistrict("Ololo");
        registration.setLocality("Ololo");
        registration.setStreet("Street");
        registration.setHouseNumber(123);
        registration.setHouseBlock(5);
        registration.setFlatNumber(123);
        registration.setIndex(12345L);
        registration.setColumns(12);
        registrationService.save(registration);

        Passport passport = new Passport();
        passport.setSeries(5);
        passport.setNumber(12345L);
        passport.setLastName("Lastname");
        passport.setFirstName("Firstname");
        passport.setMiddleName("Middlename");
        passport.setGender("МУЖ");
        passport.setBirthDate(LocalDate.of(1997, 7, 22));
        passport.setBirthPlace("Russia");
        passport.setIssuedBy("РОВД");
        passport.setDateOfIssue(LocalDate.of(1997, 7, 22));
        passport.setDivisionCode(45);
        passport.setExpirationDate(LocalDate.of(1997, 7, 22));
        passport.setRegistration(registration);
        passportService.save(passport);

        ActualRegistration actualRegistration = new ActualRegistration();
        actualRegistration.setCountry("Russia");
        actualRegistration.setRegion("Europa");
        actualRegistration.setCity("Moscow");
        actualRegistration.setDistrict("Ololo");
        actualRegistration.setLocality("Ololo");
        actualRegistration.setStreet("Street");
        actualRegistration.setHouseNumber(123);
        actualRegistration.setHouseBlock(5);
        actualRegistration.setFlatNumber(123);
        actualRegistration.setIndex(12345L);
        actualRegistrationService.save(actualRegistration);

        Profile profile = new Profile();
        profile.setPhoneNumber(1243455L);
        profile.setEmail("iseeheresy@gmail.com");
        profile.setNameOnCard("Oleg");
        profile.setInn(1000L);
        profile.setSnils(1000L);
        profile.setPassport(passport);
        profile.setActualRegistration(actualRegistration);
        profileService.save(profile);

        AccountDetailsId accountDetailsId1 = new AccountDetailsId();
        accountDetailsId1.setOwner(profile);
        accountDetailsId1.setAccountId(123456L);
        accountDetailsId1.setOwner(profile);
        accountDetailsIdService.save(accountDetailsId1);

        AccountDetailsId accountDetailsId2 = new AccountDetailsId();
        accountDetailsId2.setOwner(profile);
        accountDetailsId2.setAccountId(123456789L);
        accountDetailsIdService.save(accountDetailsId2);

    }
}
