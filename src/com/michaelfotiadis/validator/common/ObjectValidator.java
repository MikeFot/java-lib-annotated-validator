package com.michaelfotiadis.validator.common;

import com.michaelfotiadis.validator.Validator;

/**
 *
 */
public interface ObjectValidator extends Validator {
    boolean validate(final Object object);
}
