package com.michaelfotiadis.validator.annotated.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.ValDoubleEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.ValDoubleMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.ValDoubleMinValue;
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

        if (type.equals(ValDoubleMaxValue.class)) {
            return handleMaxValue(value, ((ValDoubleMaxValue) annotation).value());
        } else if (type.equals(ValDoubleMinValue.class)) {
            return handleMinValue(value, ((ValDoubleMinValue) annotation).value());
        } else if (type.equals(ValDoubleEqualsValue.class)) {
            return handleEqualsValue(value, ((ValDoubleEqualsValue) annotation).value());
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
