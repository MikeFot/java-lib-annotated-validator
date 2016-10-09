package com.michaelfotiadis.validator.annotated.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.shortnum.ShortEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.shortnum.ShortMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.shortnum.ShortMinValue;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class ShortValidator implements Validator<Short> {

    @Override
    public ValidationResult validate(final Short value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(ShortMaxValue.class)) {
            return handleMaxValue(value, ((ShortMaxValue) annotation).value());
        } else if (type.equals(ShortMinValue.class)) {
            return handleMinValue(value, ((ShortMinValue) annotation).value());
        } else if (type.equals(ShortEqualsValue.class)) {
            return handleEqualsValue(value, ((ShortEqualsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleEqualsValue(final Short number, final short equalsValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number == equalsValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMaxValue(final Short number, final short maxValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinValue(final Short number, final short minValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

}
