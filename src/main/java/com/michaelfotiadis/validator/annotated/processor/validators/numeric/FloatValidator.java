package com.michaelfotiadis.validator.annotated.processor.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnumber.FloatEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnumber.FloatMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnumber.FloatMinValue;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.processor.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class FloatValidator implements Validator<Float> {

    @Override
    public ValidationResult validate(final Float value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(FloatMaxValue.class)) {
            return handleMaxValue(value, ((FloatMaxValue) annotation).value());
        } else if (type.equals(FloatMinValue.class)) {
            return handleMinValue(value, ((FloatMinValue) annotation).value());
        } else if (type.equals(FloatEqualsValue.class)) {
            return handleEqualsValue(value, ((FloatEqualsValue) annotation).value());
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
