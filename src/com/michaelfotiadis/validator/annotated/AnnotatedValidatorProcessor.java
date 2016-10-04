package com.michaelfotiadis.validator.annotated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 */
public class AnnotatedValidatorProcessor {

    public <T> boolean validate(final T item) {

        final List<Field> fields = AnnotationParser.getDeclaredFields(item.getClass());

        for (final Field field : fields) {
            processField(field);

        }

        return false;
    }

    public void processField(final Field field) {

        final Annotation[] annotations = field.getDeclaredAnnotations();

        if (annotations.length > 0) {


        }


    }


}
