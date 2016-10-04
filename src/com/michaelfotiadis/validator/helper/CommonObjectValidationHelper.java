package com.michaelfotiadis.validator.helper;

import com.michaelfotiadis.validator.common.base.ObjectNotNullValidator;

/**
 *
 */
public abstract class CommonObjectValidationHelper {

    public boolean isNotNull(final Object item) {
        return new ObjectNotNullValidator().validate(item);
    }

}
