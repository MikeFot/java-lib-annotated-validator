package com.michaelfotiadis.validator.annotated.processor.validators;

import com.michaelfotiadis.validator.annotated.annotations.number.DoubleEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.number.DoubleMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.number.DoubleMinValue;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.lang.annotation.Annotation;

/**
 *
 */
public class DoubleAnnotationValidator implements AnnotationValidator<Double> {

    @Override
    public ValidationResult validate(final Double value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(DoubleMaxValue.class)) {
            return handleMaxValue(value, ((DoubleMaxValue) annotation).value());
        } else if (type.equals(DoubleMinValue.class)) {
            return handleMinValue(value, ((DoubleMinValue) annotation).value());
        } else if (type.equals(DoubleEqualsValue.class)) {
            return handleEqualsValue(value, ((DoubleEqualsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private ValidationResult handleEqualsValue(final Double number, final double equalsValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number == equalsValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

    private ValidationResult handleMaxValue(final Double number, final double maxValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

    private ValidationResult handleMinValue(final Double number, final double minValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

}
