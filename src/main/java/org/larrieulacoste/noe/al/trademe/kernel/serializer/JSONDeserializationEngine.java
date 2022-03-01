package org.larrieulacoste.noe.al.trademe.kernel.serializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JSONDeserializationEngine<T> implements DeserializationEngine<T> {
    private final Gson gson;

    public JSONDeserializationEngine(Gson gson) {
        this.gson = gson;
    }

    @Override
    public T apply(String strObject) {
        Type collectionType = new TypeToken<T>() {
        }.getType();
        return gson.fromJson(strObject, collectionType);
    }
}
