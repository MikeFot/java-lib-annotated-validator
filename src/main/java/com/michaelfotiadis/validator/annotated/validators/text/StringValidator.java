package com.michaelfotiadis.validator.annotated.validators.text;

import com.michaelfotiadis.validator.annotated.annotations.text.ValEmail;
import com.michaelfotiadis.validator.annotated.annotations.text.ValTextExactLength;
import com.michaelfotiadis.validator.annotated.annotations.text.ValTextIsNumeric;
import com.michaelfotiadis.validator.annotated.annotations.text.ValTextMaxLength;
import com.michaelfotiadis.validator.annotated.annotations.text.ValTextMinLength;
import com.michaelfotiadis.validator.annotated.annotations.text.ValTextNotNullOrEmpty;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;

/**
 *
 */
public class StringValidator implements Validator<String> {

    @Override
    public ValidationResult validate(final String value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(ValTextNotNullOrEmpty.class)) {
            return handleNotNullOrEmpty(value);
        } else if (type.equals(ValEmail.class)) {
            return handleEmail(value);
        } else if (type.equals(ValTextExactLength.class)) {
            return handleExactLength(value, ((ValTextExactLength) annotation).value());
        } else if (type.equals(ValTextMinLength.class)) {
            return handleMinLength(value, ((ValTextMinLength) annotation).value());
        } else if (type.equals(ValTextMaxLength.class)) {
            return handleMaxLength(value, ((ValTextMaxLength) annotation).value());
        } else if (type.equals(ValTextIsNumeric.class)) {
            return handleIsNumeric(value);
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleIsNumeric(final String value) {
        if (value == null) {
            return ValidationResult.nullValue();
        } else if (value.isEmpty()) {
            return new ValidationResult(ValidationStatus.EMPTY_STRING);
        } else if (TextValidatorUtils.isNumeric(value)) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.INVALID_VALUE);
        }
    }

    private static ValidationResult handleMaxLength(final String value, final int length) {
        if (value == null) {
            return ValidationResult.nullValue();
        } else if (value.length() <= length) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.VALUE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinLength(final String value, final int length) {
        if (value == null) {
            return ValidationResult.nullValue();
        } else if (value.length() >= length) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.VALUE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleExactLength(final String value, final int length) {
        if (value == null) {
            return ValidationResult.nullValue();
        } else if (value.length() == length) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.VALUE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleEmail(final String value) {

        final ValidationResult notEmptyResult = handleNotNullOrEmpty(value);
        if (notEmptyResult.isValid()) {
            if (TextValidatorUtils.isEmail(value)) {
                return ValidationResult.success();
            } else {
                return new ValidationResult(ValidationStatus.INVALID_VALUE);
            }
        } else {
            return notEmptyResult;
        }

    }

    private static ValidationResult handleNotNullOrEmpty(final String value) {

        if (value == null) {
            return ValidationResult.nullValue();
        } else if (value.isEmpty()) {
            return new ValidationResult(ValidationStatus.EMPTY_STRING);
        } else {
            return ValidationResult.success();
        }

    }
}
