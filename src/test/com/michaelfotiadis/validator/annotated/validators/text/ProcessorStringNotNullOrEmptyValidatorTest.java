package com.michaelfotiadis.validator.annotated.validators.text;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.text.TextNotNullOrEmpty;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
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
public class ProcessorStringNotNullOrEmptyValidatorTest {

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

        assertTrue(result.getFailedFieldNames().iterator().next().contains("textNotNullInvalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.EMPTY_STRING);
    }

    @Test
    public void validateDatesValidInvalidFailFast() throws Exception {

        final TestTextItemValidInvalid item = new TestTextItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("textNotNullInvalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.NULL_VALUE);

    }

    @Test
    public void validateDatesValidInvalid() throws Exception {

        final TestTextItemValidInvalid item = new TestTextItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item, SearchPolicy.SHALLOW, FailPolicy.CONTINUE);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("textNotNullInvalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.NULL_VALUE);

    }

    private static final class TestTextItemValid {
        @TextNotNullOrEmpty
        private static final String textNotNullValid = "1234567";
    }

    private static final class TestTextItemInvalid {
        @TextNotNullOrEmpty
        private static final String textNotNullInvalid = "";
    }

    private static final class TestTextItemValidInvalid {
        @TextNotNullOrEmpty
        private static final String textNotNullValid = "12345";
        @TextNotNullOrEmpty
        private static final String textNotNullInvalid = null;
    }


}