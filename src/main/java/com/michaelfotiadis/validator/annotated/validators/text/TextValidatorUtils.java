package com.michaelfotiadis.validator.annotated.validators.text;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.regex.Pattern;

/**
 *
 */
public final class TextValidatorUtils {

    // Taken from: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
    private static final String EMAIL_PATTERN_STRING =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN_STRING);

    private TextValidatorUtils() {
        // do not instantiate
    }


    public static boolean isEmail(final String input) {
        return PATTERN.matcher(input).matches();
    }

    public static boolean isNumeric(final String str) {
        // Taken from : http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
        final NumberFormat formatter = NumberFormat.getInstance();
        final ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }


}
