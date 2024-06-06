package com.bank.antifraud.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "fraud")
public class FraudProperties {
    int dayLimit;
    int monthLimit;
    int onetimePurchase;
    int recipientCount;
}
