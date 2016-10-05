package com.michaelfotiadis.validator.pojo.helper;

import com.michaelfotiadis.validator.pojo.common.base.ObjectNotNullValidator;

/**
 *
 */
public abstract class CommonObjectValidationHelper {

    public boolean isNotNull(final Object item) {
        return new ObjectNotNullValidator().validate(item).isValid();
    }

}
