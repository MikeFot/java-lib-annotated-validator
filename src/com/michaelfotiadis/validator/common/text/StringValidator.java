package com.michaelfotiadis.validator.common.text;

import com.michaelfotiadis.validator.Validator;

/**
 *
 */
public interface StringValidator extends Validator<String> {
    boolean validate(final String input);
}
