package com.bank.profile.dto;

import lombok.Data;

@Data
public class ProfileDto {

    private Long id;

    private Long phoneNumber;

    private String email;

    private String nameOnCard;

    private Long inn;

    private Long snils;

    private Long passportId;

    private Long actualRegistrationId;
}
