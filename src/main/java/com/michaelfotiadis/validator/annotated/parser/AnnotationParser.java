package com.michaelfotiadis.validator.annotated.parser;

import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.annotations.Category;
import com.michaelfotiadis.validator.annotated.processor.SearchPolicy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 */
public final class AnnotationParser {

    /*package*/
    static Annotation getAnnotation(final Object obj, final Class annotationType) throws ReflectiveOperationException {

        final List<Field> fields = FieldParser.getAnnotatedFields(obj, SearchPolicy.DEEP);

        for (final Field f : fields) {
            if (f.getAnnotations().length != 0 && f.getAnnotation(annotationType) != null) {
                return f.getAnnotation(annotationType);
            }
        }
        return null;
    }

    public static AnnotationCategory getCategoryOfAnnotation(final Annotation annotation) {

        final Category category = annotation.annotationType().getAnnotation(Category.class);

        if (category != null) {
            return category.type();
        } else {
            return AnnotationCategory.UNUSED;
        }

    }

}
