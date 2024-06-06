package com.bank.profile.mapper;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Registration;
import com.bank.profile.service.RegistrationService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    @Mapping(target = "registrationId", source = "registration.id")
    PassportDto toDto(Passport passport);
    @Mapping(target = "registration", expression = "java(registrationService.findById(passportDto.getRegistrationId()))")
    Passport toEntity(PassportDto passportDto, @Context RegistrationService registrationService);
}
