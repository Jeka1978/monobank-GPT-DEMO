package com.epam.monobankgptdemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JsonToMapReaderTest {

    @Mock
    private InputStream mockInputStream;

    private JsonToMapReader jsonToMapReader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jsonToMapReader = new JsonToMapReader();
    }

    @Test
    @lombok.SneakyThrows
    public void testReadJsonToMap_ValidJson() {
        // Prepare a mock InputStream
        String validJson = "{\"com.example.SampleInterface\":\"com.example.SampleImplementation\"}";
        InputStream validInputStream = new java.io.ByteArrayInputStream(validJson.getBytes());

        // Replace the getClass().getClassLoader().getResourceAsStream() call with mock
        doReturn(validInputStream).when(mockInputStream).getClass().getClassLoader().getResourceAsStream(any());

        // Act
        Map<Class<?>, Class<?>> result = jsonToMapReader.readJsonToMap("/config.json");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Class.forName("com.epam.monobankgptdemo.PessimisticMetricsCollector"), result.get(Class.forName("com.epam.monobankgptdemo.PessimisticMetricsCollector")));
    }

    @Test
    public void testReadJsonToMap_FileNotFound() {
        // Assert
        assertThrows(RuntimeException.class, () -> jsonToMapReader.readJsonToMap("nonexistent.json"));
    }
}
