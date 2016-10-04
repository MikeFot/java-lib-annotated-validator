package com.michaelfotiadis.validator.common;

import java.util.Collection;

/**
 *
 */
public class ContainsNoNulls implements CollectionValidator {

    @Override
    public boolean validate(final Collection container) {

        final ObjectNotNullValidator notNullValidator = new ObjectNotNullValidator();

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
