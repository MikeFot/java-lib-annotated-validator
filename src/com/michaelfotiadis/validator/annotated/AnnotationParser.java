package com.michaelfotiadis.validator.annotated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public final class AnnotationParser {

    private AnnotationParser() {
        // do not instantiate
    }

    public static <T> boolean parseAnnotations(final T item) {

        boolean foundAnnotation = false;

        for (final Field f : item.getClass().getDeclaredFields()) {
            final Annotation[] annotations = f.getDeclaredAnnotations();

            for (final Annotation annotation : annotations) {
                System.out.println(annotation.getClass().getSimpleName());
            }

            if (annotations.length != 0 && !foundAnnotation) {
                foundAnnotation = true;
            }

        }
        return foundAnnotation;

    }

    /**
     * Gets all declared {@link Field}s in a class
     *
     * @param type {@link Class} to be processed
     * @return {@link List} of {@link Field} found
     */
    public static List<Field> getDeclaredFields(final Class<?> type) {
        final List<Field> fields = new ArrayList<>();
        getDeclaredFieldsRecursively(fields, type);
        return fields;
    }

    /**
     * Recursive method for getting all fields, including superclass ones
     *
     * @param fields {@link List} of {@link Field} that will be modified recursively if a superclass exists
     * @param type   {@link Class} of the object to be processed
     * @return {@link List} of {@link Field} found
     */
    public static List<Field> getDeclaredFieldsRecursively(List<Field> fields, final Class<?> type) {

        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            fields = getDeclaredFieldsRecursively(fields, type.getSuperclass());
        }

        return fields;
    }

    /**
     * Utility method to check if a particular annotation exists within a class
     *
     * @param type           {@link Class} to be evaluated
     * @param annotationType {@link Class} of the annotation
     * @return true if annotation exists in class or superclass
     */
    public static boolean containsAnnotation(final Class<?> type, final Class annotationType) {

        for (final Field f : getDeclaredFields(type)) {
            if (f.getAnnotation(annotationType) != null) {
                return true;
            }
        }
        return false;

    }

}
