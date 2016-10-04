package com.michaelfotiadis.validator.pojo.common.text;

/**
 *
 */
public class MaxLengthValidator implements StringValidator {
    private final int maxLength;

    public MaxLengthValidator(final int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(final String input) {
        if (input == null) {
            return false;
        } else {
            return input.length() <= maxLength;
        }
    }
}
