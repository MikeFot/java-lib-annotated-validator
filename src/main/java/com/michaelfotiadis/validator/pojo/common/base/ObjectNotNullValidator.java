package com.michaelfotiadis.validator.pojo.common.base;

import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

/**
 *
 */
public class ObjectNotNullValidator implements ObjectValidator {

    @Override
    public ValidationResult validate(final Object item) {
        if (item != null) {
            return new ValidationResult(ValidationStatus.SUCCESS);
        } else {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        }
    }

}
