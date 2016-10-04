package com.michaelfotiadis.validator.common.base;

/**
 *
 */
public class ObjectNotNullValidator implements ObjectValidator {

    @Override
    public boolean validate(final Object object) {
        return object != null;
    }

}
