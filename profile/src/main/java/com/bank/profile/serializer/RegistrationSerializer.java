package com.bank.profile.serializer;

import com.bank.profile.entity.Registration;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RegistrationSerializer extends StdSerializer<Registration> {

    public RegistrationSerializer() {
        this(null);
    }

    protected RegistrationSerializer(Class<Registration> t) {
        super(t);
    }

    @Override
    public void serialize(Registration registration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Long passportId;
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("id", String.valueOf(registration.getId()));
        jsonGenerator.writeStringField("country", registration.getCountry());
        jsonGenerator.writeStringField("region", registration.getRegion());
        jsonGenerator.writeStringField("city", registration.getCity());
        jsonGenerator.writeStringField("district", registration.getDistrict());
        jsonGenerator.writeStringField("locality", registration.getLocality());
        jsonGenerator.writeStringField("street", registration.getStreet());
        jsonGenerator.writeStringField("houseNumber", String.valueOf(registration.getHouseNumber()));
        jsonGenerator.writeStringField("houseBlock", String.valueOf(registration.getHouseBlock()));
        jsonGenerator.writeStringField("flatNumber", String.valueOf(registration.getFlatNumber()));
        jsonGenerator.writeStringField("index", String.valueOf(registration.getIndex()));
        jsonGenerator.writeStringField("columns", String.valueOf(registration.getColumns()));

        if (registration.getPassport() != null) {
            passportId = registration.getPassport().getId();
        } else {
            passportId = null;
        }

        jsonGenerator.writeStringField("passportId", String.valueOf(passportId));
        jsonGenerator.writeEndObject();
    }
}
