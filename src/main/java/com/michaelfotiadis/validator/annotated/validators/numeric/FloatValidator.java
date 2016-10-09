package com.michaelfotiadis.validator.annotated.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.FloatEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.FloatMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.FloatMinValue;
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

        if (type.equals(FloatMaxValue.class)) {

            final FloatMaxValue clazz = ((FloatMaxValue) annotation);
            return handleMaxValue(value, clazz.max(), clazz.epsilon());

        } else if (type.equals(FloatMinValue.class)) {

            final FloatMinValue clazz = ((FloatMinValue) annotation);
            return handleMinValue(value, clazz.min(), clazz.epsilon());

        } else if (type.equals(FloatEqualsValue.class)) {

            final FloatEqualsValue clazz = ((FloatEqualsValue) annotation);
            return handleEqualsValue(value, clazz.value(), clazz.epsilon());

        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleEqualsValue(final Float number,
                                                      final float equalsValue,
                                                      final double epsilon) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (Math.abs(number - equalsValue) <= epsilon) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMaxValue(final Float number,
                                                   final float maxValue,
                                                   final double epsilon) {

        if (number == null) {
            return ValidationResult.nullValue();
        } else if (Math.abs(number - maxValue) <= epsilon || number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinValue(final Float number,
                                                   final float minValue,
                                                   final double epsilon) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (Math.abs(number - minValue) <= epsilon || number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

}
