package com.michaelfotiadis.validator.pojo.common;

import com.michaelfotiadis.validator.pojo.common.text.StringValidator;
import com.michaelfotiadis.validator.pojo.common.text.UrlValidator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 *
 */
public class UrlValidatorTest {

    private static final String[] INVALID_DATA = {
            "username@domain.com",
            "",
            null,
            "://www.domain.com",};

    private static final String[] VALID_DATA = {
            "www.domain.com",
            "domain.com",
            "www.domain.com/",
            "domain.com/",
            "www.domain.co.za",
            "domain.co.za",
            "www.domain.co.za/",
            "domain.co.za/",

            "192.168.0.1",
            "192.168.0.1/",

            "www.domain.co.za:8080/",
            "domain.co.za:8080/",

            "192.168.0.1:8080",
            "192.168.0.1:8080/",

            "www.domain.com/a",
            "domain.com/a",

            "www.domain.com/a.html",
            "domain.com/a.html",
            "www.domain.com/a.html/b.html",
            "domain.com/a.html/b.html"};

    private static final String[] VALID_PREFIX = {
            "http://",
            "https://",
            "file://",
            "rtsp://"
    };

    private StringValidator mValidator;

    @Before
    public void setUp() throws Exception {
        mValidator = new UrlValidator();
    }

    @Test
    public void testValidateInValid() throws Exception {
        for (final String string : INVALID_DATA) {
            assertFalse("This should NOT have validated: '" + string + "'", mValidator.validate(string).isValid());
        }
    }

    @Test
    public void testValidateValid() throws Exception {
        for (final String string : VALID_DATA) {
            for (final String prefix : VALID_PREFIX) {
                final String url = prefix + string;
                assertTrue("This should have validated: '" + url + "'", mValidator.validate(url).isValid());
            }

        }
    }
}