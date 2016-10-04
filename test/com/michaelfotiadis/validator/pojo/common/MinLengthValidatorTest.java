package com.michaelfotiadis.validator.pojo.common;

import com.michaelfotiadis.validator.pojo.common.text.MinLengthValidator;
import com.michaelfotiadis.validator.pojo.common.text.StringValidator;

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
        assertFalse(mValidator.validate("a").isValid());
        assertFalse(mValidator.validate(" ").isValid());
        assertFalse(mValidator.validate("").isValid());
        assertFalse(mValidator.validate(null).isValid());
    }

    @Test
    public void testValidateValid() throws Exception {
        assertTrue(mValidator.validate("  ").isValid());
        assertTrue(mValidator.validate("aa").isValid());
        assertTrue(mValidator.validate("   ").isValid());
        assertTrue(mValidator.validate("aaa").isValid());
    }
}