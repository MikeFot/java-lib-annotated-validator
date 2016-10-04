package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.annotations.integer.IntegerMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.integer.IntegerMinValue;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.lang.annotation.Annotation;

/**
 *
 */
public class IntegerAnnotationValidator {

    public ValidationResult validate(final Integer value, final Annotation annotation) {

        if (annotation.annotationType().equals(IntegerMaxValue.class)) {
            return handleMaxValue(value, ((IntegerMaxValue) annotation).value());
        } else if (annotation.annotationType().equals(IntegerMinValue.class)) {
            return handleMinValue(value, ((IntegerMinValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private ValidationResult handleMaxValue(final Integer value, final int maxValue) {
        if (value == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (value <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.NUMBER_OUT_OF_RANGE);
        }
    }

    private ValidationResult handleMinValue(final Integer value, final int minValue) {
        if (value == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (value >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.NUMBER_OUT_OF_RANGE);
        }
    }

}
