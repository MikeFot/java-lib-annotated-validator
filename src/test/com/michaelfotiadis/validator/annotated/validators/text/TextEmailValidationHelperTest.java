package com.michaelfotiadis.validator.annotated.validators.text;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 *
 */
public class TextEmailValidationHelperTest {

    private static final String[] EMAIL_INVALID_DATA = {
            "username",
            "username@.com.my",
            "username@domain.a",
            "username123@.com",
            "username123@.com.com",
            ".username@domain.com",
            "username()*@domain.com",
            "username@%*.com",
            "username..2002@domain.com",
            "username.@domain.com",
            "username@domain@domain.com",
            "username@domain.com.1a"};
    private static final String[] EMAIL_VALID_DATA = {
            "username@domain.com",
            "username-999@domain.com",
            "username.999@domain.com",
            "username999@domain.com",
            "username-100@domain.net",
            "username.100@domain.co.uk",
            "username@1.com",
            "username@domain.com.com",
            "username+100@domain.com",
            "username-100@domain-domain.com"};

    @Test
    public void validateEmailInValid() throws Exception {
        for (final String string : EMAIL_INVALID_DATA) {
            assertFalse("This should NOT have validated: '" + string + "'", TextEmailValidationHelper.isEmail(string));
        }
    }

    @Test
    public void validateEmailValid() throws Exception {
        for (final String string : EMAIL_VALID_DATA) {
            assertTrue("This should have validated: '" + string + "'", TextEmailValidationHelper.isEmail(string));
        }
    }

}