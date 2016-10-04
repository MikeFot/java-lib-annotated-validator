package com.michaelfotiadis.validator.pojo.helper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class StringValidationHelperTest {

    private StringValidationHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new StringValidationHelper();
    }


    @Test
    public void hasMaxLength() throws Exception {

        final String text8 = "12345678";

        final int length = text8.length();
        assertEquals(8, text8.length());
        assertFalse("Max length -3 should be failing", helper.hasMaxLength(text8, length - 3));
        assertTrue("Max length equals length should be passing", helper.hasMaxLength(text8, length));
        assertTrue("Max length +1 should be passing", helper.hasMaxLength(text8, length + 1));

    }

    @Test
    public void hasMinLength() throws Exception {

        final String text8 = "12345678";
        final int length = text8.length();

        assertTrue("Min length -3 should be passing", helper.hasMinLength(text8, length - 3));
        assertTrue("Min length equals length should be passing", helper.hasMinLength(text8, length));
        assertFalse("Min length +1 should be failing", helper.hasMinLength(text8, length + 1));

    }

    @Test
    public void hasLengthBetween() throws Exception {

        final String text8 = "12345678";
        final int length = text8.length();

        assertFalse("Exceeding min length should be failing", helper.hasLengthBetween(text8, length + 5, length + 10));
        assertFalse("Less than max length should be failing", helper.hasLengthBetween(text8, 0, length - 1));
        assertTrue("Between 7 and 8 should be passing", helper.hasLengthBetween(text8, length - 1, length + 1));


    }

    @Test
    public void isEmail() throws Exception {
        assertFalse("Not an email should be failing", helper.isEmail("fdsfdfe"));
        assertTrue("Email should be passing", helper.isEmail("test@test.ac.com"));
    }

    @Test
    public void isUrl() throws Exception {
        assertFalse("Not a url should be failing", helper.isUrl("ree"));
        assertTrue("Url should be passing", helper.isUrl("www.domain.com"));
    }

}