package com.epam.monobankgptdemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Map;

public class JsonToMapReader {

    private final ObjectMapper objectMapper;

    public JsonToMapReader() {
        this.objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    public Map<Class<?>, Class<?>> readJsonToMap(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new RuntimeException("File not found: " + fileName);
        }

        return objectMapper.readValue(
                inputStream,
                new TypeReference<Map<Class<?>, Class<?>>>() {}
        );
    }
}

