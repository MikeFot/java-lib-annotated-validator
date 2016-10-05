package com.michaelfotiadis.validator.pojo.common.text;

import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

/**
 *
 */
public class NotEmptyValidator implements StringValidator {

    @Override
    public ValidationResult validate(final String input) {

        if (input == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (input.isEmpty()) {
            return new ValidationResult(ValidationStatus.EMPTY_STRING);
        } else {
            return ValidationResult.success();
        }

    }

}
