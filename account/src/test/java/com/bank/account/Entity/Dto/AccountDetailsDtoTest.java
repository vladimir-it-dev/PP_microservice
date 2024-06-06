package com.bank.account.Entity.Dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountDetailsDtoTest {

    @Test
    void testAccountDetailsDto() {
        AccountDetailsDto dto = new AccountDetailsDto();
        dto.setId(1L);
        dto.setPassportId(123456L);
        dto.setAccountNumber(1234567890L);
        dto.setBankDetailsId(1L);
        dto.setMoney(new BigDecimal("1000"));
        dto.setNegativeBalance(false);
        dto.setProfileId(1L);

        assertEquals(1L, dto.getId());
        assertEquals(123456L, dto.getPassportId());
        assertEquals(1234567890L, dto.getAccountNumber());
        assertEquals(1L, dto.getBankDetailsId());
        assertEquals(new BigDecimal("1000"), dto.getMoney());
        assertFalse(dto.getNegativeBalance());
        assertEquals(1L, dto.getProfileId());

        dto.setNegativeBalance(true);
        assertTrue(dto.getNegativeBalance());
    }
}
