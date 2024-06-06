package com.bank.profile.mapper;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.service.ProfileService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDetailsIdMapper {

    @Mapping(source = "owner.id", target = "profileId")
    AccountDetailsIdDto toDto(AccountDetailsId accountDetailsId);

    @Mapping(target = "owner", expression = "java(profileService.findById(accountDetailsIdDto.getProfileId()))")
    AccountDetailsId toEntity(AccountDetailsIdDto accountDetailsIdDto, @Context ProfileService profileService);
}
