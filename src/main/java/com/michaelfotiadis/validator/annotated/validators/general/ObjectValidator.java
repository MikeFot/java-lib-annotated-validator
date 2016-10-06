package com.michaelfotiadis.validator.annotated.validators.general;

import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class ObjectValidator implements Validator<Object> {

    @Override
    public ValidationResult validate(final Object item, final Annotation annotation) {

        if (item == null) {
            return ValidationResult.nullValue();
        } else {
            return ValidationResult.success();
        }
    }

}
