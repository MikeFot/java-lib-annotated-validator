package com.michaelfotiadis.validator.pojo.common.text;

/**
 *
 */
public class MinLengthValidator implements StringValidator {
    private final int minLength;

    public MinLengthValidator(final int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean validate(final String input) {
        if (input == null) {
            return false;
        } else {
            return input.length() >= minLength;
        }
    }
}
