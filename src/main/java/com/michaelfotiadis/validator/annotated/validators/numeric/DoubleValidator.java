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
            final DoubleMaxValue clazz = ((DoubleMaxValue) annotation);
            return handleMaxValue(value, clazz.max(), clazz.epsilon());
        } else if (type.equals(DoubleMinValue.class)) {
            final DoubleMinValue clazz = ((DoubleMinValue) annotation);
            return handleMinValue(value, clazz.min(), clazz.epsilon());
        } else if (type.equals(DoubleEqualsValue.class)) {
            final DoubleEqualsValue clazz = ((DoubleEqualsValue) annotation);
            return handleEqualsValue(value, clazz.value(), clazz.epsilon());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleEqualsValue(final Double number,
                                                      final double equalsValue,
                                                      final double epsilon) {

        if (number == null) {
            return ValidationResult.nullValue();
        } else if (Math.abs(number - equalsValue) <= epsilon) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMaxValue(final Double number,
                                                   final double maxValue,
                                                   final double epsilon) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (Math.abs(number - maxValue) <= epsilon || number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinValue(final Double number,
                                                   final double minValue,
                                                   final double epsilon) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (Math.abs(number - minValue) <= epsilon || number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.DOUBLE_OUT_OF_RANGE);
        }
    }

}
