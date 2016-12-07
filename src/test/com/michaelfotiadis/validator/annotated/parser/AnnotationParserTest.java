package com.michaelfotiadis.validator.annotated.parser;

import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.IntegerMinValue;

import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class AnnotationParserTest {


    @Test
    public void getCategoryOfAnnotation() throws Exception {

        TestInteger testInteger = new TestInteger();

        Annotation annotation = AnnotationParser.getAnnotation(testInteger, IntegerMinValue.class);

        assertNotNull(annotation);

        AnnotationCategory category = AnnotationParser.getCategoryOfAnnotation(annotation);

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