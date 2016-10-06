package com.michaelfotiadis.validator.annotated;

import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.ValIntegerMinValue;
import com.michaelfotiadis.validator.annotated.parser.AnnotationParser;

import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class AnnotationParserTest {

    @Test
    public void containsAnnotation() throws Exception {

        assertFalse(AnnotationParser.containsAnnotation("Sss".getClass(), ValIntegerMinValue.class));

        final TestInteger testInteger = new TestInteger();
        assertTrue(AnnotationParser.containsAnnotation(testInteger.getClass(), ValIntegerMinValue.class));

    }

    @Test
    public void containsAnnotationExtended() throws Exception {
        final ExtendedTestInteger extendedTestInteger = new ExtendedTestInteger();
        assertTrue(AnnotationParser.containsAnnotation(extendedTestInteger.getClass(), ValIntegerMinValue.class));

    }

    @Test
    public void getCategoryOfAnnotation() throws Exception {

        TestInteger testInteger = new TestInteger();

        Annotation annotation = AnnotationParser.getAnnotation(testInteger.getClass(), ValIntegerMinValue.class);

        assertNotNull(annotation);

        AnnotationCategory category = AnnotationParser.getCategoryOfAnnotation(annotation);

        assertEquals(AnnotationCategory.INTEGER, category);

    }
    @SuppressWarnings("InnerClassMayBeStatic")
    private abstract class BaseTestInteger {
        @ValIntegerMinValue(1)
        int value;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class ExtendedTestInteger extends BaseTestInteger {
        // NOOP
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class TestInteger {
        @ValIntegerMinValue(1)
        int value;
    }

}