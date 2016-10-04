package com.michaelfotiadis.validator.pojo.common.base;

import com.michaelfotiadis.validator.pojo.Validator;

/**
 *
 */
public interface ObjectValidator extends Validator {
    boolean validate(final Object object);
}
