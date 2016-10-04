package com.michaelfotiadis.validator.common.base;

import com.michaelfotiadis.validator.Validator;

/**
 *
 */
public interface ObjectValidator extends Validator {
    boolean validate(final Object object);
}
