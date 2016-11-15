package com.michaelfotiadis.validator.annotated.validators.numeric;

import com.michaelfotiadis.validator.annotated.annotations.numeric.bytenum.ByteEqualsValue;
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

        if (type.equals(ByteEqualsValue.class)) {
            return handleEqualsValue(value, ((ByteEqualsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleEqualsValue(final Byte value, final byte equalsValue) {
        if (value == null) {
            return ValidationResult.nullValue();
        } else if (value.equals(equalsValue)) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.BYTE_OUT_OF_RANGE);
        }
    }


}
