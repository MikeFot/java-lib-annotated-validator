package com.michaelfotiadis.validator.annotated.validators.text;

import java.util.regex.Pattern;

/**
 *
 */
public final class TextNumericValidatorHelper {

    private static final String EXPRESSION = "-?\\d+([.]\\d+)?";
    private static final Pattern PATTERN = Pattern.compile(EXPRESSION);

    private TextNumericValidatorHelper() {
        // do not instantiate
    }

    public static boolean isNumeric(final String input) {
        if (input == null) {
            return false;
        } else {
            return PATTERN.matcher(input).matches();
        }
    }


}
