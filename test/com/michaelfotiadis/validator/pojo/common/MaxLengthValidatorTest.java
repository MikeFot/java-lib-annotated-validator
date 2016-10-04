package com.michaelfotiadis.validator.pojo.common;

import com.michaelfotiadis.validator.pojo.common.text.MaxLengthValidator;
import com.michaelfotiadis.validator.pojo.common.text.StringValidator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 *
 */
public class MaxLengthValidatorTest {

    private StringValidator mValidator;

    @Before
    public void setUp() throws Exception {
        mValidator = new MaxLengthValidator(1);
    }

    @Test
    public void testValidateInValid() throws Exception {
        assertFalse(mValidator.validate(null));
        assertFalse(mValidator.validate("  "));
        assertFalse(mValidator.validate("aa"));
    }

    @Test
    public void testValidateValid() throws Exception {
        assertTrue(mValidator.validate(""));
        assertTrue(mValidator.validate("a"));
        assertTrue(mValidator.validate(" "));
    }
}