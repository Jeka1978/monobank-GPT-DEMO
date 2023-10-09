package com.epam.monobankgptdemo;

import lombok.SneakyThrows;
import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntAnnotationObjectConfigurator implements ObjectConfigurator {

    private final Random random;

    public InjectRandomIntAnnotationObjectConfigurator() {
        this.random = new Random();
    }

    @Override
    @SneakyThrows
    public void configure(Object t) {
        for (Field field : t.getClass().getDeclaredFields()) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (annotation != null) {
                int min = annotation.min();
                int max = annotation.max();
                int randomValue = min + random.nextInt(max - min);
                field.setAccessible(true);
                field.setInt(t, randomValue);
            }
        }
    }
}

