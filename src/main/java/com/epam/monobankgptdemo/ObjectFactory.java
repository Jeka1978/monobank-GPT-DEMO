package com.epam.monobankgptdemo;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

public class ObjectFactory {

    private static final ObjectFactory INSTANCE = new ObjectFactory();
    private final Config config;
    private final Set<ObjectConfigurator> configurators;

    @SneakyThrows
    private ObjectFactory() {
        String basePackage = "com.epam";
        this.config = new Config(basePackage); // Replace with your base package
        Reflections reflections = new Reflections(basePackage); // Again, replace with your package

        this.configurators = reflections.getSubTypesOf(ObjectConfigurator.class)
                .stream()
                .map(this::instantiate)
                .collect(Collectors.toSet());
    }

    public static ObjectFactory getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        T t = create(type);

        configurators.forEach(configurator -> configurator.configure(t));

        return t;
    }

    @SneakyThrows
    private <T> T create(Class<T> type) {
        Class<?> implClass = config.getImplClass(type);
        return type.cast(implClass.getDeclaredConstructor().newInstance());
    }

    @SneakyThrows
    private <T> T instantiate(Class<T> type) {
        return type.getDeclaredConstructor().newInstance();
    }
}


