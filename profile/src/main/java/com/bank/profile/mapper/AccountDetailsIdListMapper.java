package com.bank.profile.mapper;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsId;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AccountDetailsIdMapper.class)
public interface AccountDetailsIdListMapper {
    List<AccountDetailsIdDto> toDto(List<AccountDetailsId> accountDetailsIdList);
}
