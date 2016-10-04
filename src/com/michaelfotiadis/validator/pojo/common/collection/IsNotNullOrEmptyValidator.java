package com.michaelfotiadis.validator.pojo.common.collection;

import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.util.Collection;

/**
 *
 */
public class IsNotNullOrEmptyValidator implements CollectionValidator {

    @Override
    public ValidationResult validate(final Collection container) {

        if (container == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (container.isEmpty()) {
            return new ValidationResult(ValidationStatus.EMPTY_COLLECTION);
        } else {
            return ValidationResult.success();
        }

    }

}
