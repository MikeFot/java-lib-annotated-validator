package com.michaelfotiadis.validator.pojo.common.text;

import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

/**
 *
 */
public class MinLengthValidator implements StringValidator {
    private final int minLength;

    public MinLengthValidator(final int minLength) {
        this.minLength = minLength;
    }

    @Override
    public ValidationResult validate(final String input) {

        if (input == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (input.length() >= minLength) {
            return new ValidationResult(ValidationStatus.SUCCESS);
        } else {
            return new ValidationResult(ValidationStatus.INVALID_VALUE);
        }

    }
}
