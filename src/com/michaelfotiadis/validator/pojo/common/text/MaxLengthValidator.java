package com.michaelfotiadis.validator.pojo.common.text;

import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

/**
 *
 */
public class MaxLengthValidator implements StringValidator {
    private final int maxLength;

    public MaxLengthValidator(final int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public ValidationResult validate(final String input) {

        if (input == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (input.length() <= maxLength) {
            return new ValidationResult(ValidationStatus.SUCCESS);
        } else {
            return new ValidationResult(ValidationStatus.INVALID_VALUE);
        }

    }
}
