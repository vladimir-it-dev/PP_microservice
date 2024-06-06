package com.bank.profile.serializer;

import com.bank.profile.entity.AccountDetailsId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AccountDetailsIdSerializer extends StdSerializer<AccountDetailsId> {
    public AccountDetailsIdSerializer() {
        this(null);
    }

    protected AccountDetailsIdSerializer(Class<AccountDetailsId> t) {
        super(t);
    }

    @Override
    public void serialize(AccountDetailsId accountDetailsId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Long profileId;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(accountDetailsId.getId()));
        jsonGenerator.writeStringField("accountId", String.valueOf(accountDetailsId.getAccountId()));
        jsonGenerator.writeStringField("profileId", String.valueOf(accountDetailsId.getOwner().getId()));

        if (accountDetailsId.getOwner() != null) {
            profileId = accountDetailsId.getOwner().getId();
        } else {
            profileId = null;
        }

        jsonGenerator.writeStringField("profileId", String.valueOf(profileId));
        jsonGenerator.writeEndObject();
    }
}
