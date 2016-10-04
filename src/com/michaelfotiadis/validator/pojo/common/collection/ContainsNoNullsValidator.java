package com.michaelfotiadis.validator.pojo.common.collection;

import com.michaelfotiadis.validator.pojo.common.base.ObjectNotNullValidator;

import java.util.Collection;

/**
 *
 */
public class ContainsNoNullsValidator implements CollectionValidator {

    @Override
    public boolean validate(final Collection container) {

        final ObjectNotNullValidator notNullValidator = new ObjectNotNullValidator();

        if (!notNullValidator.validate(container)) {
            return false;
        }

        if (notNullValidator.validate(container)) {
            for (final Object item : container) {
                if (!notNullValidator.validate(item)) {
                    return false;
                }
            }
        }
        return true;
    }

}
