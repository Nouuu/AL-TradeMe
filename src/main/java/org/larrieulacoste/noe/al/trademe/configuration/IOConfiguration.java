package org.larrieulacoste.noe.al.trademe.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.kernel.io.*;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.JSONDeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.JSONSerializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.SerializationEngine;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.List;

@Dependent
final class IOConfiguration {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Produces
    SerializationEngine<List<Contractor>> serializer() {
        return new JSONSerializationEngine<>(gson);
    }

    @Produces
    DeserializationEngine<List<Contractor>> deserializer() {
        return new JSONDeserializationEngine<>(gson);
    }

    @Produces
    Reader reader(InjectionPoint injectionPoint) {
        String path = "repositories/" + injectionPoint.getAnnotated().getAnnotation(FileQualifier.class).value();
        return new FileReader(path);
    }

    @Produces
    Writer writer(InjectionPoint injectionPoint) {
        String path = "repositories/" + injectionPoint.getAnnotated().getAnnotation(FileQualifier.class).value();
        return new FileWriter(path);
    }

}
