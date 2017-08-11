package com.example.hasee.fruitcalendar.http.factory;

import com.example.hasee.fruitcalendar.http.common.ParameterizedTypeUtil;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by LOVE on 2016/4/29 0029.
 */
final class StringResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    StringResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        if (String.class == ParameterizedTypeUtil.getParameterizedType(adapter.getClass())) {
            return (T) value.string();
        } else {
            JsonReader jsonReader = gson.newJsonReader(value.charStream());
            try {
                return adapter.read(jsonReader);
            } finally {
                value.close();
            }
        }


    }
}


