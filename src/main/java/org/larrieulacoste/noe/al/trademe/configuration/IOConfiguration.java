package org.larrieulacoste.noe.al.trademe.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.larrieulacoste.noe.al.trademe.kernel.io.*;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.JSONDeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.JSONSerializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.SerializationEngine;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
final class IOConfiguration {

    private final ObjectMapper mapper = new ObjectMapper();

    @Produces
    SerializationEngine serializer() {
        return new JSONSerializationEngine(mapper);
    }

    @Produces
    DeserializationEngine deserializer() {
        return new JSONDeserializationEngine(mapper);
    }

    @Produces
    @FileQualifier("")
    Reader reader(InjectionPoint injectionPoint) {
        String path = "repositories/" + injectionPoint.getAnnotated().getAnnotation(FileQualifier.class).value();
        return new FileReader(path);
    }

    @Produces
    @FileQualifier("")
    Writer writer(InjectionPoint injectionPoint) {
        String path = "repositories/" + injectionPoint.getAnnotated().getAnnotation(FileQualifier.class).value();
        return new FileWriter(path);
    }

}
