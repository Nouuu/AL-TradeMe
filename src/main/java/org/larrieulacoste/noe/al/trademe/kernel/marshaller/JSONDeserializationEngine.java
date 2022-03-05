package org.larrieulacoste.noe.al.trademe.kernel.marshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.larrieulacoste.noe.al.trademe.kernel.exception.DeserializeException;

import java.util.Objects;

public class JSONDeserializationEngine implements DeserializationEngine {
    private final ObjectMapper mapper;

    public JSONDeserializationEngine(ObjectMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper);
    }

    @Override
    public <T> T apply(String strObject, Class<T> objectClass) {
        if (strObject == null) {
            return null;
        }
        try {
            return mapper.readValue(strObject,objectClass);
        } catch (JsonProcessingException e) {
            throw new DeserializeException(e.getMessage());
        }
    }
}
