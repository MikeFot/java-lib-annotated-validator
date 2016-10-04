package com.michaelfotiadis.validator.pojo.common.base;

/**
 *
 */
public class ObjectNotNullValidator implements ObjectValidator {

    @Override
    public boolean validate(final Object object) {
        return object != null;
    }

}
