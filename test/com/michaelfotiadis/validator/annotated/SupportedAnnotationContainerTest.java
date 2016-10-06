package com.michaelfotiadis.validator.annotated;

import com.michaelfotiadis.validator.annotated.annotations.numeric.integernumber.IntegerMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernumber.IntegerMinValue;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class SupportedAnnotationContainerTest {

    @Test
    public void isSupported() throws Exception {

        assertFalse(SupportedAnnotationContainer.isSupported(SupportedAnnotationContainer.class));
        assertTrue(SupportedAnnotationContainer.isSupported(IntegerMinValue.class));
        assertTrue(SupportedAnnotationContainer.isSupported(IntegerMaxValue.class));

    }

    @Test
    public void getSupportedClasses() throws Exception {
        assertNotEquals(0, SupportedAnnotationContainer.getSupportedClasses());
    }


}