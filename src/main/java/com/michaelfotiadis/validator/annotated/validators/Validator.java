package com.michaelfotiadis.validator.annotated.validators;

import com.michaelfotiadis.validator.annotated.model.ValidationResult;

import java.lang.annotation.Annotation;

/**
 *
 */
public interface Validator<T> {

    ValidationResult validate(T item, Annotation annotation);

}
