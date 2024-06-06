package com.bank.profile.mapper;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.Profile;
import com.bank.profile.service.ActualRegistrationService;
import com.bank.profile.service.PassportService;
import com.bank.profile.service.RegistrationService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(source = "passport.id", target = "passportId")
    @Mapping(source = "actualRegistration.id", target = "actualRegistrationId")
    ProfileDto toDto(Profile profile);

    @Mapping(target = "passport", expression = "java(passportService.findById(profileDto.getPassportId()))")
    @Mapping(target = "actualRegistration", expression = "java(actualRegistrationService.findById(profileDto.getActualRegistrationId()))")
    Profile toEntity(ProfileDto profileDto, @Context PassportService passportService, @Context ActualRegistrationService actualRegistrationService);
}
