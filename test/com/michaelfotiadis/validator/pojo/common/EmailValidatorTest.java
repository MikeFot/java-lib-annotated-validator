package com.michaelfotiadis.validator.pojo.common;


import com.michaelfotiadis.validator.pojo.common.text.EmailValidator;
import com.michaelfotiadis.validator.pojo.common.text.StringValidator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


/**
 *
 */
public class EmailValidatorTest {
    private static final String[] INVALID_DATA = {
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
    private static final String[] VALID_DATA = {
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
    private StringValidator mValidator;

    @Before
    public void setUp() throws Exception {
        mValidator = new EmailValidator();
    }

    @Test
    public void testValidateInValid() throws Exception {
        for (final String string : INVALID_DATA) {
            assertFalse("This should NOT have validated: '" + string + "'", mValidator.validate(string));
        }
    }

    @Test
    public void testValidateValid() throws Exception {
        for (final String string : VALID_DATA) {
            assertTrue("This should have validated: '" + string + "'", mValidator.validate(string));
        }
    }


}