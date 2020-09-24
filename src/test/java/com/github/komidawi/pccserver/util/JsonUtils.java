package com.github.komidawi.pccserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String toJsonString(final Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T fromJsonString(final String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }
}
