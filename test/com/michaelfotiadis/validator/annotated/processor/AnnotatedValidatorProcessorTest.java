package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.ValidationMapResult;
import com.michaelfotiadis.validator.annotated.annotations.integer.IntegerMinValue;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class AnnotatedValidatorProcessorTest {

    @Test
    public void validate() throws Exception {

        final AnnotatedValidatorProcessor processor = new AnnotatedValidatorProcessor();

        final TestInteger testInteger = new TestInteger();
        testInteger.value = 5;
        final ValidationMapResult result1 = processor.validate(testInteger);
        assertTrue(result1.isValid());

        testInteger.value = 1;
        final ValidationMapResult result2 = processor.validate(testInteger);
        assertFalse(result2.isValid());

    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class TestInteger {
        @IntegerMinValue(2)
        int value;
    }

}