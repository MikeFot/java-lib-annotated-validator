package com.michaelfotiadis.validator.annotated.validators.conditional;

import com.michaelfotiadis.validator.annotated.annotations.conditional.BooleanEqualsValue;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class BooleanValidator implements Validator<Boolean> {

    @Override
    public ValidationResult validate(final Boolean value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(BooleanEqualsValue.class)) {
            return handleEqualsValue(value, ((BooleanEqualsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleEqualsValue(final Boolean bool, final boolean equalsValue) {
        if (bool == null) {
            return ValidationResult.nullValue();
        } else if (bool.equals(equalsValue)) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

}
