package com.michaelfotiadis.validator.annotated.validators.text;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.text.TextUrl;
import com.michaelfotiadis.validator.annotated.processor.AnnotatedValidatorProcessor;
import com.michaelfotiadis.validator.annotated.processor.FailPolicy;
import com.michaelfotiadis.validator.annotated.processor.SearchPolicy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class ProcessorStringUrlValidatorTest {

    private AnnotatedValidatorProcessor processor;


    @Before
    public void setUp() throws Exception {
        processor = new AnnotatedValidatorProcessor();
    }

    @Test
    public void validateDatesValid() throws Exception {

        final TestTextItemValid item = new TestTextItemValid();

        final ValidationResultsContainer result = processor.validate(item);

        assertTrue(result.isValid());

    }

    @Test
    public void validateDatesInvalid() throws Exception {

        final TestTextItemInvalid item = new TestTextItemInvalid();

        final ValidationResultsContainer result = processor.validate(item);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());

        assertTrue(result.getFailedFieldNames().iterator().next().contains("textUrlInvalid"));

    }

    @Test
    public void validateDatesValidInvalidFailFast() throws Exception {

        final TestTextItemValidInvalid item = new TestTextItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("textUrlInvalid"));

    }

    @Test
    public void validateDatesValidInvalid() throws Exception {

        final TestTextItemValidInvalid item = new TestTextItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item, SearchPolicy.SHALLOW, FailPolicy.CONTINUE);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("textUrlInvalid"));

    }

    private static final class TestTextItemValid {
        @TextUrl
        private static final String textUrlValid = "https://youtube.com";
    }

    private static final class TestTextItemInvalid {
        @TextUrl
        private static final String textUrlInvalid = "https://youtube";
    }

    private static final class TestTextItemValidInvalid {
        @TextUrl
        private static final String textUrlValid = "www.fdsfd.com";
        @TextUrl
        private static final String textUrlInvalid = "not_valid";
    }


}