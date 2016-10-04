package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.ValidationMapResult;
import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.parser.AnnotationParser;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AnnotatedValidatorProcessor {

    private static final IntegerAnnotationValidator sIntegerValidator = new IntegerAnnotationValidator();

    public <T> ValidationMapResult validate(final T item) {

        final List<Field> fields = AnnotationParser.getDeclaredFields(item.getClass());

        final ValidationMapResult result = new ValidationMapResult();

        for (final Field field : fields) {
            try {
                final List<ValidationResult> results = validateField(item, field);
                if (!areAllResultsValid(results)) {
                    result.put(field.getName(), getFailures(results));
                }
            } catch (final IllegalAccessException e) {
                result.put(field.getName(), ValidationStatus.EXCEPTION);
            }
        }

        return result;
    }

    public <T> List<ValidationResult> validateField(final T item, final Field field) throws IllegalAccessException {

        final Annotation[] annotations = field.getDeclaredAnnotations();
        final List<ValidationResult> results = new ArrayList<>();

        if (annotations != null && annotations.length > 0) {
            for (final Annotation annotation : annotations) {
                final AnnotationCategory category = AnnotationParser.getCategoryOfAnnotation(annotation);
                final ValidationResult result;
                switch (category) {
                    case UNUSED:
                        result = ValidationResult.success();
                        break;
                    case INTEGER:
                        final Integer value = field.getInt(item);
                        result = sIntegerValidator.validate(value, annotation);
                        break;
                    default:
                        result = ValidationResult.success();
                }
                results.add(result);
            }

        }

        return results;

    }

    public List<ValidationStatus> getFailures(final List<ValidationResult> results) {

        final List<ValidationStatus> statuses = new ArrayList<>();

        for (final ValidationResult result : results) {
            // fail if even one of the results is invalid
            if (!result.getStatus().equals(ValidationStatus.SUCCESS)) {
                statuses.add(result.getStatus());
            }
        }
        return statuses;

    }

    public boolean areAllResultsValid(final List<ValidationResult> results) {
        for (final ValidationResult result : results) {
            // fail if even one of the results is invalid
            if (!result.isValid()) {
                return false;
            }
        }
        return true;
    }

}
