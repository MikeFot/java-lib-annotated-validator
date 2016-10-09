package com.michaelfotiadis.validator.annotated.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleMinValue;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class DoubleValidator implements Validator<Double> {

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

    private static ValidationResult handleEqualsValue(final Double number, final double equalsValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number == equalsValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMaxValue(final Double number, final double maxValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinValue(final Double number, final double minValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

}
