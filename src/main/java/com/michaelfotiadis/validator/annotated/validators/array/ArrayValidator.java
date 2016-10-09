package com.michaelfotiadis.validator.annotated.validators.array;

import com.michaelfotiadis.validator.annotated.annotations.array.ArrayContainsNoNulls;
import com.michaelfotiadis.validator.annotated.annotations.array.ArrayIsNotEmpty;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class ArrayValidator implements Validator<Class<?>[]> {

    @Override
    public ValidationResult validate(final Class<?>[] array, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(ArrayIsNotEmpty.class)) {
            return handleIsNotEmpty(array);
        } else if (type.equals(ArrayContainsNoNulls.class)) {
            return handleContainsNoNulls(array);
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleContainsNoNulls(final Class<?>[] array) {

        if (array == null) {
            return ValidationResult.nullValue();
        } else {
            for (final Object item : array) {
                if (item == null) {
                    return new ValidationResult(ValidationStatus.COLLECTION_CONTAINS_NULL);
                }
            }
            return ValidationResult.success();
        }

    }

    private static ValidationResult handleIsNotEmpty(final Class<?>[] array) {

        if (array == null) {
            return ValidationResult.nullValue();
        } else if (array.length == 0) {
            return new ValidationResult(ValidationStatus.EMPTY_COLLECTION);
        } else {
            return ValidationResult.success();
        }

    }
}
