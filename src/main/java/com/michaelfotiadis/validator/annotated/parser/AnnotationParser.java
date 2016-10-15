package com.michaelfotiadis.validator.annotated.parser;

import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.annotations.Category;
import com.michaelfotiadis.validator.annotated.processor.SearchPolicy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 */
public final class AnnotationParser {

    public <T> boolean parseAnnotations(final T item) {

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
     * Utility method to check if a particular annotation exists within a class
     *
     * @param obj           {@link Object} to be evaluated
     * @param annotationType {@link Class} of the annotation
     * @return true if annotation exists in class or superclass
     */
    public boolean containsAnnotation(final Object obj, final Class annotationType) {
        return getAnnotation(obj, annotationType) != null;
    }

    public Annotation getAnnotation(final Object obj, final Class annotationType) {
        for (final Field f : new FieldParser().getDeclaredFields(obj, SearchPolicy.SHALLOW)) {
            if (f.getAnnotation(annotationType) != null) {
                return f.getAnnotation(annotationType);
            }
        }
        return null;
    }

    public AnnotationCategory getCategoryOfAnnotation(final Annotation annotation) {

        final Category category = annotation.annotationType().getAnnotation(Category.class);

        if (category != null) {
            return category.type();
        } else {
            return AnnotationCategory.UNUSED;
        }

    }

}
