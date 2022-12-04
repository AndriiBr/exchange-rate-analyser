package com.exchange.rate.analyser.util;

import java.util.Arrays;

/**
 * TestValidationHelper class provides utility methods to validate different aspects of target class.
 */
public class TestValidationHelper {

    /**
     * Check that provided class has field with provided name.
     * @param object object to validate
     * @param name field name
     * @return TRUE - if field with provided name exist. FALSE - if not.
     */
    public static boolean verifyFieldExist(Object object, String name) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .anyMatch(f -> f.getName().equals(name));
    }

    /**
     * Check that provided class has field with provided type.
     * @param object object to validate
     * @param targetClass target class type
     * @return TRUE - if field with provided type exist. FALSE - if not.
     */
    public static <T> boolean verifyFieldType(Object object, Class<T> targetClass) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .anyMatch(field -> field.getType().equals(targetClass));
    }
}
