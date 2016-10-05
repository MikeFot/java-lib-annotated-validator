package com.michaelfotiadis.validator.pojo.helper;

import com.michaelfotiadis.validator.pojo.common.text.EmailValidator;
import com.michaelfotiadis.validator.pojo.common.text.MaxLengthValidator;
import com.michaelfotiadis.validator.pojo.common.text.MinLengthValidator;
import com.michaelfotiadis.validator.pojo.common.text.UrlValidator;

/**
 *
 */
public class StringValidationHelper extends CommonObjectValidationHelper {

    public boolean hasMaxLength(final String input, final int maxLength) {
        return new MaxLengthValidator(maxLength).validate(input).isValid();
    }

    public boolean hasMinLength(final String input, final int minLength) {
        return new MinLengthValidator(minLength).validate(input).isValid();
    }

    public boolean hasLengthBetween(final String input, final int minLength, final int maxLength) {
        if (minLength > maxLength) {
            throw new IllegalStateException("Validator Min length is larger than max length!");
        }

        return hasMinLength(input, minLength) && hasMaxLength(input, maxLength);
    }

    public boolean isEmail(final String input) {
        return new EmailValidator().validate(input).isValid();
    }

    public boolean isUrl(final String input) {
        return new UrlValidator().validate(input).isValid();
    }

}
