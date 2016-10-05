package com.michaelfotiadis.validator.annotated.processor.validators;

import com.michaelfotiadis.validator.annotated.annotations.number.IntegerEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.number.IntegerMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.number.IntegerMinValue;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.lang.annotation.Annotation;

/**
 *
 */
public class IntegerAnnotationValidator implements AnnotationValidator<Integer> {

    @Override
    public ValidationResult validate(final Integer value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(IntegerMaxValue.class)) {
            return handleMaxValue(value, ((IntegerMaxValue) annotation).value());
        } else if (type.equals(IntegerMinValue.class)) {
            return handleMinValue(value, ((IntegerMinValue) annotation).value());
        } else if (type.equals(IntegerEqualsValue.class)) {
            return handleEqualsValue(value, ((IntegerEqualsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private ValidationResult handleEqualsValue(final Integer number, final int equalsValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number == equalsValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

    private ValidationResult handleMaxValue(final Integer number, final int maxValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

    private ValidationResult handleMinValue(final Integer number, final int minValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

}
