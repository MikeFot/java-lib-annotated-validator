package com.michaelfotiadis.validator.annotated.parser;

import com.michaelfotiadis.validator.annotated.processor.SearchPolicy;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class FieldParser {

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
    private final Set<Object> visitedFields;

    public FieldParser() {
        this.visitedFields = new HashSet<>();
    }

    public static boolean isWrapperType(final Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        final Set<Class<?>> primitiveWrappers = new HashSet<>();
        primitiveWrappers.add(Boolean.class);
        primitiveWrappers.add(Character.class);
        primitiveWrappers.add(Byte.class);
        primitiveWrappers.add(Short.class);
        primitiveWrappers.add(Integer.class);
        primitiveWrappers.add(Long.class);
        primitiveWrappers.add(Float.class);
        primitiveWrappers.add(String.class);
        primitiveWrappers.add(Number.class);
        primitiveWrappers.add(BigInteger.class);
        primitiveWrappers.add(BigDecimal.class);
        primitiveWrappers.add(String.class);
        primitiveWrappers.add(Double.class);
        primitiveWrappers.add(Void.class);
        return primitiveWrappers;
    }

    /**
     * Gets all declared {@link Field}s in a class
     *
     * @param obj {@link Object} to be processed
     * @return {@link List} of {@link Field} found
     */
    public <T> List<Field> getDeclaredFields(final T obj, final SearchPolicy searchPolicy) {
        final List<Field> fields = new ArrayList<>();
        getDeclaredFieldsRecursively(fields, obj.getClass());

        if (searchPolicy.equals(SearchPolicy.DEEP)) {
            final List<Field> deepFields = new ArrayList<>();
            for (final Field field : fields) {

                try {
                    field.setAccessible(true);
                    final Object o1 = field.get(obj);
                    deepFields.addAll(checkObjectIterable(o1));

                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(false);
                }

            }
            fields.addAll(deepFields);


        }

        visitedFields.clear();
        return fields;
    }

    private List<Field> checkObjectIterable(final Object obj) {

        final List<Field> fields = new ArrayList<>();
        if (obj != null) {
            if (obj instanceof Map) {
                final Map map = (Map) obj;
                for (final Object value : map.values()) {
                    if (!isWrapperType(value.getClass())) {
                        fields.addAll(getDeclaredFields(value, SearchPolicy.DEEP));
                    }
                }
            } else if (obj instanceof Collection) {
                for (final Object value : (Collection) obj) {
                    if (!isWrapperType(value.getClass())) {
                        fields.addAll(getDeclaredFields(value, SearchPolicy.DEEP));
                    }
                }
            } else if (obj.getClass().isArray()) {
                for (final Object value : unpack(obj)) {
                    if (!isWrapperType(value.getClass())) {
                        fields.addAll(getDeclaredFields(value, SearchPolicy.DEEP));
                    }
                }
            }
        }
        return fields;
    }

    public static Object[] unpack(final Object array) {
        final Object[] array2 = new Object[Array.getLength(array)];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = Array.get(array, i);
        }
        return array2;
    }

    /**
     * Recursive method for getting all fields, including superclass ones
     *
     * @param fields {@link List} of {@link Field} that will be modified recursively if a superclass exists
     * @param type   {@link Class} of the object to be processed
     * @return {@link List} of {@link Field} found
     */
    public List<Field> getDeclaredFieldsRecursively(List<Field> fields,
                                                    final Class<?> type) {

        final List<Field> declaredFields = Arrays.asList(type.getDeclaredFields());

        for (final Field field : declaredFields) {
            if (visitedFields.contains(field)) {
                System.out.println("Found cyclic reference for field " + field.getName() + " in class " + field.getDeclaringClass());
            } else {
                fields.add(field);
            }
        }

        if (type.getSuperclass() != null) {
            fields = getDeclaredFieldsRecursively(fields, type.getSuperclass());
        }

        return fields;
    }

}
