package com.michaelfotiadis.validator.annotated.processor.validators;

import com.michaelfotiadis.validator.pojo.results.ValidationResult;

import java.lang.annotation.Annotation;

/**
 *
 */
public interface AnnotationValidator<T> {

    ValidationResult validate(T item, Annotation annotation);

}
