package com.michaelfotiadis.validator.annotated.processor.validators;

import com.michaelfotiadis.validator.annotated.annotations.number.FloatEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.number.FloatMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.number.FloatMinValue;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.lang.annotation.Annotation;

/**
 *
 */
public class FloatAnnotationValidator implements AnnotationValidator<Float> {

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

    private ValidationResult handleEqualsValue(final Float number, final float equalsValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number == equalsValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

    private ValidationResult handleMaxValue(final Float number, final float maxValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

    private ValidationResult handleMinValue(final Float number, final float minValue) {
        if (number == null) {
            return new ValidationResult(ValidationStatus.NULL_VALUE);
        } else if (number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.FLOAT_OUT_OF_RANGE);
        }
    }

}
