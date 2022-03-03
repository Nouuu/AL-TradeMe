package org.larrieulacoste.noe.al.trademe.kernel.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.larrieulacoste.noe.al.trademe.kernel.exception.SerializeException;

import java.util.Objects;

public class JSONSerializationEngine implements SerializationEngine {
    private final ObjectMapper mapper;

    public JSONSerializationEngine(ObjectMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper);
    }

    @Override
    public <T> String apply(T object) {
        try {
            return mapper.writeValueAsString(
                    Objects.requireNonNull(object)
            );
        } catch (JsonProcessingException e) {
            throw new SerializeException(e.getMessage());
        }
    }
}
