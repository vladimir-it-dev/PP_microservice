package com.bank.account.Entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AccountDetailsTest {

    @Test
    void testAccountDetailsBuilder() {
        AccountDetails accountDetails = AccountDetails.builder()
                .id(1L)
                .passportId(12345L)
                .accountNumber(98765L)
                .bankDetailsId(123L)
                .money(BigDecimal.valueOf(10000))
                .negativeBalance(false)
                .profileId(111L)
                .build();

        assertThat(accountDetails.getId()).isEqualTo(1L);
        assertThat(accountDetails.getPassportId()).isEqualTo(12345L);
        assertThat(accountDetails.getAccountNumber()).isEqualTo(98765L);
        assertThat(accountDetails.getBankDetailsId()).isEqualTo(123L);
        assertThat(accountDetails.getMoney()).isEqualTo(BigDecimal.valueOf(10000));
        assertThat(accountDetails.getNegativeBalance()).isEqualTo(false);
        assertThat(accountDetails.getProfileId()).isEqualTo(111L);
    }

    @Test
    void testAccountDetailsGettersAndSetters() {
        AccountDetails accountDetails = new AccountDetails();

        accountDetails.setId(1L);
        accountDetails.setPassportId(12345L);
        accountDetails.setAccountNumber(98765L);
        accountDetails.setBankDetailsId(123L);
        accountDetails.setMoney(BigDecimal.valueOf(10000));
        accountDetails.setNegativeBalance(false);
        accountDetails.setProfileId(111L);

        assertThat(accountDetails.getId()).isEqualTo(1L);
        assertThat(accountDetails.getPassportId()).isEqualTo(12345L);
        assertThat(accountDetails.getAccountNumber()).isEqualTo(98765L);
        assertThat(accountDetails.getBankDetailsId()).isEqualTo(123L);
        assertThat(accountDetails.getMoney()).isEqualTo(BigDecimal.valueOf(10000));
        assertThat(accountDetails.getNegativeBalance()).isEqualTo(false);
        assertThat(accountDetails.getProfileId()).isEqualTo(111L);
    }
}
