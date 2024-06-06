package com.bank.profile.serializer;

import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.entity.Profile;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class ProfileSerializer extends StdSerializer<Profile> {
    public ProfileSerializer() {
        this(null);
    }

    protected ProfileSerializer(Class<Profile> t) {
        super(t);
    }

    @Override
    public void serialize(Profile profile, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Long> accountDetailsIdList = new ArrayList<>();
        Long actualRegistrationId;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(profile.getId()));
        jsonGenerator.writeStringField("phoneNumber", String.valueOf(profile.getPhoneNumber()));
        jsonGenerator.writeStringField("email", profile.getEmail());
        jsonGenerator.writeStringField("nameOfCard", profile.getNameOnCard());
        jsonGenerator.writeStringField("inn", String.valueOf(profile.getInn()));
        jsonGenerator.writeStringField("snils", String.valueOf(profile.getSnils()));
        jsonGenerator.writeStringField("passportId", String.valueOf(profile.getPassport().getId()));

        if (profile.getAccounts() != null) {
            for (AccountDetailsId accountDetailsId: profile.getAccounts()) {
                accountDetailsIdList.add(accountDetailsId.getId());
            }
        } else {
            accountDetailsIdList = null;
        }

        if (profile.getActualRegistration() != null) {
            actualRegistrationId = profile.getActualRegistration().getId();
        } else {
            actualRegistrationId = null;
        }

        jsonGenerator.writeStringField("actualRegistrationId", String.valueOf(actualRegistrationId));
        jsonGenerator.writeStringField("accountsIdList", String.valueOf(accountDetailsIdList));
        jsonGenerator.writeEndObject();
    }
}
