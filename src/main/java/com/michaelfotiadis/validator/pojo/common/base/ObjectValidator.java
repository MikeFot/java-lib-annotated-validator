package com.michaelfotiadis.validator.pojo.common.base;

import com.michaelfotiadis.validator.pojo.Validator;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;

/**
 *
 */
public interface ObjectValidator extends Validator {

    ValidationResult validate(final Object object);

}
