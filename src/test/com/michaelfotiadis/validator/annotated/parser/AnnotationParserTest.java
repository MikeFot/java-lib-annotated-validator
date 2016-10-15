package com.michaelfotiadis.validator.annotated.parser;

import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.IntegerMinValue;

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
        AnnotationParser parser = new AnnotationParser();
        assertFalse(parser.containsAnnotation("Sss".getClass(), IntegerMinValue.class));

        final TestInteger testInteger = new TestInteger();
        assertTrue(parser.containsAnnotation(testInteger.getClass(), IntegerMinValue.class));

    }

    @Test
    public void containsAnnotationExtended() throws Exception {
        final ExtendedTestInteger extendedTestInteger = new ExtendedTestInteger();
        AnnotationParser parser = new AnnotationParser();
        assertTrue(parser.containsAnnotation(extendedTestInteger.getClass(), IntegerMinValue.class));

    }

    @Test
    public void getCategoryOfAnnotation() throws Exception {

        TestInteger testInteger = new TestInteger();

        AnnotationParser parser = new AnnotationParser();
        Annotation annotation = parser.getAnnotation(testInteger, IntegerMinValue.class);

        assertNotNull(annotation);

        AnnotationCategory category = parser.getCategoryOfAnnotation(annotation);

        assertEquals(AnnotationCategory.INTEGER, category);

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