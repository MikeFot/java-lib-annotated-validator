package com.michaelfotiadis.validator.annotated.processor.validators;

import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.lang.annotation.Annotation;

/**
 *
 */
public class ObjectAnnotationValidator implements AnnotationValidator<Object> {

    @Override
    public ValidationResult validate(final Object item, final Annotation annotation) {

        if (item == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else {
            return ValidationResult.success();
        }
    }

}
