package com.michaelfotiadis.validator.annotated.validators.text;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 *
 */
public class TextNumericValidatorHelperTest {

    private static final String[] NUMERIC_VALID_DATA = {
            "1",
            "214143513",
            "424243.544646",
            "-1",
            "0",
            "-4343f",
            "-0493483434343.23232",

    };
    private static final String[] NUMERIC_INVALID_DATA = {
            "bob",
            "21414351ds3",
            "424243.54.464.6",
            "a-1",
            "--",
            "-4343I am an invalid number",
            "",
            "@",
            "this is a text!!!!",
            "!"
    };


    @Test
    public void isStringNumericValid() throws Exception {
        for (final String string : NUMERIC_VALID_DATA) {
            assertTrue("This should have validated: '" + string + "'", TextNumericValidatorHelper.isNumeric(string));
        }
    }

    @Test
    public void isStringNumericInvalid() throws Exception {
        for (final String string : NUMERIC_INVALID_DATA) {
            assertFalse("This should NOT have validated: '" + string + "'", TextNumericValidatorHelper.isNumeric(string));
        }
    }

}