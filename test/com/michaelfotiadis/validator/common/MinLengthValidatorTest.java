package com.michaelfotiadis.validator.common;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 *
 */
public class MinLengthValidatorTest {

    private StringValidator mValidator;

    @Before
    public void setUp() throws Exception {
        mValidator = new MinLengthValidator(2);
    }

    @Test
    public void testValidateInValid() throws Exception {
        assertFalse(mValidator.validate("a"));
        assertFalse(mValidator.validate(" "));
        assertFalse(mValidator.validate(""));
        assertFalse(mValidator.validate(null));
    }

    @Test
    public void testValidateValid() throws Exception {
        assertTrue(mValidator.validate("  "));
        assertTrue(mValidator.validate("aa"));
        assertTrue(mValidator.validate("   "));
        assertTrue(mValidator.validate("aaa"));
    }
}