package org.larrieulacoste.noe.al.trademe.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.larrieulacoste.noe.al.trademe.kernel.io.*;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.JSONDeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.JSONSerializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.SerializationEngine;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
final class MarshallerConfiguration {

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Produces
    SerializationEngine serializer() {
        return new JSONSerializationEngine(mapper);
    }

    @Produces
    DeserializationEngine deserializer() {
        return new JSONDeserializationEngine(mapper);
    }
}
