package com.epam.monobankgptdemo;

import java.util.HashMap;
import java.util.Map;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Config {

    private Map<Class<?>, Class<?>> interfaceToImplementation = new HashMap<>();
    private final Reflections reflections;

    public Config(String basePackage) {
        reflections = new Reflections(basePackage);
        interfaceToImplementation = new JsonToMapReader().readJsonToMap("config.json");
        // Initialize the mapping from interfaces to implementations here, if needed.
        // For example:
        // interfaceToImplementation.put(SampleInterface.class, SampleImplementation.class);
    }

    public Class<?> getImplClass(Class<?> ifc) {
        Class<?> implClass = interfaceToImplementation.get(ifc);

        if (implClass == null) {
            Set<?> subTypes = reflections.getSubTypesOf(ifc);

            if (subTypes.size() > 1) {
                throw new RuntimeException("Multiple implementations found for " + ifc.getName());
            } else if (subTypes.size() == 1) {
                implClass = (Class<?>) subTypes.iterator().next();
            } else {
                throw new RuntimeException("No implementation found for " + ifc.getName());
            }
        }

        return implClass;
    }
}


