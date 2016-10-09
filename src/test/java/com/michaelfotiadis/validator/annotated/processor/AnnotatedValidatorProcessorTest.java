package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class AnnotatedValidatorProcessorTest {

    private AnnotatedValidatorProcessor processor;


    @Before
    public void setUp() throws Exception {
        processor = new AnnotatedValidatorProcessor();
    }

    @Test
    public void canSupportAllAnnotationCategories() {

        for (final AnnotationCategory category : AnnotationCategory.values()) {
            assertTrue("Failed to support category: " + category.toString(), processor.canSupportAnnotationCategory(category));
        }

    }



}