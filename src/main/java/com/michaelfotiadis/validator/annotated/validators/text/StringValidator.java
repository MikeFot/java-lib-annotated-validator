package com.michaelfotiadis.validator.annotated.validators.text;

import com.michaelfotiadis.validator.annotated.annotations.text.TextDateFormat;
import com.michaelfotiadis.validator.annotated.annotations.text.TextEmail;
import com.michaelfotiadis.validator.annotated.annotations.text.TextExactLength;
import com.michaelfotiadis.validator.annotated.annotations.text.TextIsNumeric;
import com.michaelfotiadis.validator.annotated.annotations.text.TextMatchesExpression;
import com.michaelfotiadis.validator.annotated.annotations.text.TextMaxLength;
import com.michaelfotiadis.validator.annotated.annotations.text.TextMinLength;
import com.michaelfotiadis.validator.annotated.annotations.text.TextNotNullOrEmpty;
import com.michaelfotiadis.validator.annotated.annotations.text.TextUrl;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 */
public class StringValidator implements Validator<String> {

    @Override
    public ValidationResult validate(final String value, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(TextNotNullOrEmpty.class)) {
            return handleNotNullOrEmpty(value);
        } else if (type.equals(TextEmail.class)) {
            return handleEmail(value);
        } else if (type.equals(TextUrl.class)) {
            return handleUrl(value);
        } else if (type.equals(TextMatchesExpression.class)) {
            return handleMatchesExpression(value, ((TextMatchesExpression) annotation).expression());
        } else if (type.equals(TextExactLength.class)) {
            return handleExactLength(value, ((TextExactLength) annotation).value());
        } else if (type.equals(TextMinLength.class)) {
            return handleMinLength(value, ((TextMinLength) annotation).value());
        } else if (type.equals(TextMaxLength.class)) {
            return handleMaxLength(value, ((TextMaxLength) annotation).value());
        } else if (type.equals(TextIsNumeric.class)) {
            return handleIsNumeric(value);
        } else if (type.equals(TextDateFormat.class)) {
            return handleIsOfDateFormat(value, ((TextDateFormat) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleMatchesExpression(final String value, final String expression) {

        final ValidationResult notEmptyResult = handleNotNullOrEmpty(value);
        if (notEmptyResult.isValid()) {

            if (Pattern.compile(expression).matcher(value).matches()) {
                return ValidationResult.success();
            } else {
                return new ValidationResult(ValidationStatus.PATTERN_DID_NOT_MATCH);
            }
        } else {
            return notEmptyResult;
        }
    }

    private static ValidationResult handleUrl(final String value) {

        final ValidationResult notEmptyResult = handleNotNullOrEmpty(value);
        if (notEmptyResult.isValid()) {
            if (TextUrlValidationHelper.isUrl(value)) {
                return ValidationResult.success();
            } else {
                return new ValidationResult(ValidationStatus.PATTERN_DID_NOT_MATCH);
            }
        } else {
            return notEmptyResult;
        }
    }

    private static ValidationResult handleIsNumeric(final String value) {
        if (value == null) {
            return ValidationResult.nullValue();
        } else if (value.isEmpty()) {
            return new ValidationResult(ValidationStatus.EMPTY_STRING);
        } else if (TextNumericValidatorHelper.isNumeric(value)) {
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
            if (TextEmailValidationHelper.isEmail(value)) {
                return ValidationResult.success();
            } else {
                return new ValidationResult(ValidationStatus.PATTERN_DID_NOT_MATCH);
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

    private static ValidationResult handleIsOfDateFormat(final String text, final String format) {

        if (format == null || format.length() == 0) {
            return ValidationResult.failure();
        } else if (text == null) {
            return ValidationResult.failure();
        } else {
            final Date result;
            try {
                result = new SimpleDateFormat(format).parse(text);
                if (result != null) {
                    return ValidationResult.success();
                } else {
                    return ValidationResult.failure();
                }
            } catch (final ParseException e) {
                return ValidationResult.failure();
            }


        }
    }

}
