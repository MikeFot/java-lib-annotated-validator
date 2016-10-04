package com.michaelfotiadis.validator.pojo.common.text;

import com.michaelfotiadis.validator.pojo.Validator;

/**
 *
 */
public interface StringValidator extends Validator<String> {
    boolean validate(final String input);
}
