package com.michaelfotiadis.validator.annotated.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.bytenum.ByteEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.bytenum.ByteMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.bytenum.ByteMinValue;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class ByteValidator implements Validator<Byte> {

    @Override
    public ValidationResult validate(final Byte value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(ByteMaxValue.class)) {
            return handleMaxValue(value, ((ByteMaxValue) annotation).value());
        } else if (type.equals(ByteMinValue.class)) {
            return handleMinValue(value, ((ByteMinValue) annotation).value());
        } else if (type.equals(ByteEqualsValue.class)) {
            return handleEqualsValue(value, ((ByteEqualsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleEqualsValue(final Byte number, final byte equalsValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number.equals(equalsValue)) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMaxValue(final Byte number, final byte maxValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number.compareTo(maxValue) > 0) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinValue(final Byte number, final byte minValue) {
        if (number == null) {
            return ValidationResult.nullValue();
        } else if (number.compareTo(minValue) < 0) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INTEGER_OUT_OF_RANGE);
        }
    }

}
