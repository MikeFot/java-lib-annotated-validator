package com.michaelfotiadis.validator.pojo.common.text;

/**
 *
 */
public class NotEmptyValidator implements StringValidator {

    @Override
    public boolean validate(final String input) {
        if (input != null && !input.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
