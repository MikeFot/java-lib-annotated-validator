package com.michaelfotiadis.validator.annotated.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.ValIntegerEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.ValIntegerMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.ValIntegerMinValue;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class IntegerValidator implements Validator<Integer> {

    @Override
    public ValidationResult validate(final Integer value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(ValIntegerMaxValue.class)) {
            return handleMaxValue(value, ((ValIntegerMaxValue) annotation).value());
        } else if (type.equals(ValIntegerMinValue.class)) {
            return handleMinValue(value, ((ValIntegerMinValue) annotation).value());
        } else if (type.equals(ValIntegerEqualsValue.class)) {
            return handleEqualsValue(value, ((ValIntegerEqualsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleEqualsValue(final Integer number, final int equalsValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number == equalsValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMaxValue(final Integer number, final int maxValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number <= maxValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinValue(final Integer number, final int minValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number >= minValue) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

}
