package com.michaelfotiadis.validator.helper;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class ValidationHelperTest {

    @Test
    public void getCollectionHelper() throws Exception {
        assertNotNull(ValidationHelper.getCollectionHelper());
    }

}