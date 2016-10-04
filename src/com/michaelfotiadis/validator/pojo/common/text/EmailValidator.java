package com.michaelfotiadis.validator.pojo.common.text;

import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.util.regex.Pattern;

public class EmailValidator implements StringValidator {
    // Taken from: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/

    private static final String EMAIL_PATTERN_STRING =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN_STRING);

    @Override
    public ValidationResult validate(final String input) {

        final ValidationResult notEmptyResult = new NotEmptyValidator().validate(input);

        if (notEmptyResult.isValid()) {
            if (PATTERN.matcher(input).matches()) {
                return new ValidationResult(ValidationStatus.SUCCESS);
            } else {
                return new ValidationResult(ValidationStatus.INVALID_VALUE);
            }
        } else {
            return notEmptyResult;
        }

    }
}