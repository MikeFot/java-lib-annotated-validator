package com.michaelfotiadis.validator.common.text;

import java.util.regex.Pattern;

public class EmailValidator implements StringValidator {
    // Taken from: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/

    private static final String EMAIL_PATTERN_STRING =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN_STRING);

    @Override
    public boolean validate(final String input) {
        if (input == null || input.isEmpty()) {
            return false;
        } else {
            return PATTERN.matcher(input).matches();
        }
    }
}