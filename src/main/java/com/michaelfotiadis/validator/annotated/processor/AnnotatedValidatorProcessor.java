package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.parser.AnnotationParser;
import com.michaelfotiadis.validator.annotated.processor.validators.ObjectValidator;
import com.michaelfotiadis.validator.annotated.processor.validators.Validator;
import com.michaelfotiadis.validator.annotated.processor.validators.numeric.DoubleValidator;
import com.michaelfotiadis.validator.annotated.processor.validators.numeric.FloatValidator;
import com.michaelfotiadis.validator.annotated.processor.validators.numeric.IntegerValidator;
import com.michaelfotiadis.validator.annotated.processor.validators.numeric.ShortValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public final class AnnotatedValidatorProcessor {

    private final Map<AnnotationCategory, Validator> mValidatorMap;

    public AnnotatedValidatorProcessor() {
        mValidatorMap = new HashMap<>();
        mValidatorMap.put(AnnotationCategory.GENERIC, new ObjectValidator());
        mValidatorMap.put(AnnotationCategory.INTEGER, new IntegerValidator());
        mValidatorMap.put(AnnotationCategory.SHORT, new ShortValidator());
        mValidatorMap.put(AnnotationCategory.FLOAT, new FloatValidator());
        mValidatorMap.put(AnnotationCategory.DOUBLE, new DoubleValidator());

    }

    public <T> ValidationResultsContainer validate(final T item) {

        final List<Field> fields = AnnotationParser.getDeclaredFields(item.getClass());

        final ValidationResultsContainer result = new ValidationResultsContainer();

        for (final Field field : fields) {
            try {
                final List<ValidationResult> results = validateField(item, field);
                if (!AnnotationProcessorUtils.areAllResultsValid(results)) {
                    result.put(getHumanName(item, field), AnnotationProcessorUtils.getFailures(results));
                }
            } catch (final IllegalAccessException e) {
                result.put(getHumanName(item, field), ValidationStatus.EXCEPTION);
            }
        }

        return result;
    }

    private static <T> String getHumanName(final T item, final Field field) {
        return String.format("%s of type %s in object of type %s",
                field.getName(),
                field.getType().getName(),
                item.getClass().getName());
    }

    public <T> List<ValidationResult> validateField(final T item, final Field field) throws IllegalAccessException {

        final Annotation[] annotations = field.getDeclaredAnnotations();
        final List<ValidationResult> results = new ArrayList<>();

        if (annotations != null && annotations.length > 0) {
            for (final Annotation annotation : annotations) {
                final AnnotationCategory category = AnnotationParser.getCategoryOfAnnotation(annotation);
                final ValidationResult result;

                final Object o = field.get(item);

                if (mValidatorMap.containsKey(category)) {
                    //noinspection unchecked
                    result = mValidatorMap.get(category).validate(o, annotation);
                } else {
                    result = ValidationResult.success();
                }


                results.add(result);
            }

        }

        return results;

    }

}
