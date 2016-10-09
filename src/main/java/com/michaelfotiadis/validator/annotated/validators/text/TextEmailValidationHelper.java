package com.michaelfotiadis.validator.annotated.validators.text;

import java.util.regex.Pattern;

/**
 *
 */
public final class TextEmailValidationHelper {

    // Taken from: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
    private static final String EMAIL_PATTERN_STRING =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN_STRING);

    private TextEmailValidationHelper() {
        // do not instantiate
    }


    public static boolean isEmail(final String input) {
        return PATTERN.matcher(input).matches();
    }

}
