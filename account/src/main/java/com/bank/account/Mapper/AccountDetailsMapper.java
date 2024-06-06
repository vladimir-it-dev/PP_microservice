package com.bank.account.Mapper;

import com.bank.account.Entity.AccountDetails;
import com.bank.account.Entity.Dto.AccountDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountDetailsMapper {
    AccountDetailsMapper INSTANCE = Mappers.getMapper(AccountDetailsMapper.class);

    AccountDetailsDto toDto(AccountDetails accountDetails);

    AccountDetails toEntity(AccountDetailsDto accountDetailsDto);

}
