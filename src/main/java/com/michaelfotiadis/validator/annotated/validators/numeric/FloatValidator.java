package com.michaelfotiadis.validator.annotated.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.ValFloatEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.ValFloatMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.ValFloatMinValue;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class FloatValidator implements Validator<Float> {

    @Override
    public ValidationResult validate(final Float value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(ValFloatMaxValue.class)) {
            return handleMaxValue(value, ((ValFloatMaxValue) annotation).value());
        } else if (type.equals(ValFloatMinValue.class)) {
            return handleMinValue(value, ((ValFloatMinValue) annotation).value());
        } else if (type.equals(ValFloatEqualsValue.class)) {
            return handleEqualsValue(value, ((ValFloatEqualsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleEqualsValue(final Float number, final float equalsValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number == equalsValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMaxValue(final Float number, final float maxValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinValue(final Float number, final float minValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

}
