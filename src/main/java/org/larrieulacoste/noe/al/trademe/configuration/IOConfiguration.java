package org.larrieulacoste.noe.al.trademe.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.JSONSerializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.SerializationEngine;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import java.util.List;

@Dependent
final class IOConfiguration {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Produces
    SerializationEngine<List<Contractor>> serializer() {
        return new JSONSerializationEngine<>(gson);
    }
}
