package com.michaelfotiadis.validator.pojo.common.text;

import com.michaelfotiadis.validator.pojo.Validator;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;

/**
 *
 */
public interface StringValidator extends Validator<String> {

    ValidationResult validate(final String input);

}
