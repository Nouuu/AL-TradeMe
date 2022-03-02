package org.larrieulacoste.noe.al.trademe.kernel.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.larrieulacoste.noe.al.trademe.kernel.exception.DeserializeException;

public class JSONDeserializationEngine implements DeserializationEngine {
    private final ObjectMapper mapper;

    public JSONDeserializationEngine(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T apply(String strObject, Class<T> objectClass) {
        try {
            return mapper.readValue(strObject,objectClass);
        } catch (JsonProcessingException e) {
            throw new DeserializeException(e.getMessage());
        }
    }
}
