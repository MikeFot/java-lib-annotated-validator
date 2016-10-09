package com.michaelfotiadis.validator.annotated.validators.general;

import com.michaelfotiadis.validator.annotated.annotations.general.IsNull;
import com.michaelfotiadis.validator.annotated.annotations.general.NotNull;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class ObjectValidator implements Validator<Object> {

    @Override
    public ValidationResult validate(final Object item, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(NotNull.class)) {
            return handleNotNull(item);
        } else if (type.equals(IsNull.class)) {
            return handleIsNull(item);
        } else {
            return ValidationResult.failure();
        }


    }

    private static ValidationResult handleIsNull(final Object item) {
        if (item == null) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INVALID_VALUE);
        }
    }

    private static ValidationResult handleNotNull(final Object item) {
        if (item == null) {
            return ValidationResult.nullValue();
        } else {
            return ValidationResult.success();
        }
    }

}
