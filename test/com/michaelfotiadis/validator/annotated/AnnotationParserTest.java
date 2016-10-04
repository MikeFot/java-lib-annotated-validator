package com.michaelfotiadis.validator.annotated;

import com.michaelfotiadis.validator.annotated.annotations.IntegerMinValue;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class AnnotationParserTest {

    @Test
    public void containsAnnotation() throws Exception {

        assertFalse(AnnotationParser.containsAnnotation("Sss".getClass(), IntegerMinValue.class));

        final TestInteger testInteger = new TestInteger();
        assertTrue(AnnotationParser.containsAnnotation(testInteger.getClass(), IntegerMinValue.class));

    }

    @Test
    public void containsAnnotationExtended() throws Exception {
        final ExtendedTestInteger extendedTestInteger = new ExtendedTestInteger();
        assertTrue(AnnotationParser.containsAnnotation(extendedTestInteger.getClass(), IntegerMinValue.class));

    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private abstract class BaseTestInteger {
        @IntegerMinValue(1)
        int value;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class ExtendedTestInteger extends BaseTestInteger {
        // NOOP
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class TestInteger {
        @IntegerMinValue(1)
        int value;
    }

}