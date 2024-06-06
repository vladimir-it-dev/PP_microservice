package com.bank.profile.dto;

import com.bank.profile.entity.Passport;
import lombok.Data;

@Data
public class RegistrationDto {

    private Long id;

    private String country;

    private String region;

    private String city;

    private String district;

    private String locality;

    private String street;

    private Integer houseNumber;

    private Integer houseBlock;

    private Integer flatNumber;

    private Long index;

    private Integer columns;
}
