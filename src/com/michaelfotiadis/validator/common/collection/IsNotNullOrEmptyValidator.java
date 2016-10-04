package com.michaelfotiadis.validator.common.collection;

import java.util.Collection;

/**
 *
 */
public class IsNotNullOrEmptyValidator implements CollectionValidator {

    @Override
    public boolean validate(final Collection container) {
        return container != null && !container.isEmpty();
    }

}
