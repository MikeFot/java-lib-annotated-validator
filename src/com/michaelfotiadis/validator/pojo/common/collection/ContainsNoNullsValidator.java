package com.michaelfotiadis.validator.pojo.common.collection;

import com.michaelfotiadis.validator.pojo.common.base.ObjectNotNullValidator;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.util.Collection;

/**
 *
 */
public class ContainsNoNullsValidator implements CollectionValidator {

    @Override
    public ValidationResult validate(final Collection container) {

        final ObjectNotNullValidator notNullValidator = new ObjectNotNullValidator();

        final ValidationResult notNullResult = notNullValidator.validate(container);
        if (notNullResult.isValid()) {
            for (final Object item : container) {
                final ValidationResult itemResult = notNullValidator.validate(item);
                if (!itemResult.isValid()) {
                    return itemResult;
                }
            }
            return new ValidationResult(ValidationStatus.SUCCESS);
        } else {
            return notNullResult;
        }
    }

}
