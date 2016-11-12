package com.michaelfotiadis.validator.annotated.validators.text;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.text.TextEmail;
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
public class ProcessorStringEmailValidatorTest {

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
        assertTrue(result.getFailedFieldNames().iterator().next().contains("textEmailInvalid"));

    }

    @Test
    public void validateDatesValidInvalid() throws Exception {

        final TestTextItemValidInvalid item = new TestTextItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("textEmailInvalid"));

    }

    @Test
    public void validateDatesValidInvalidDeep() throws Exception {

        final TestTextItemValidInvalidDeep item = new TestTextItemValidInvalidDeep();

        final ValidationResultsContainer resultShallow = processor.validate(item, SearchPolicy.SHALLOW, FailPolicy.CONTINUE);

        assertTrue(resultShallow.isValid());
        assertEquals(0, resultShallow.getFailedStatuses().size());

        final ValidationResultsContainer resultDeep = processor.validate(item, SearchPolicy.DEEP, FailPolicy.CONTINUE);

        assertFalse(resultDeep.isValid());
        assertEquals(1, resultDeep.getFailedStatuses().size());

    }

    private static final class TestTextItemValid {
        @TextEmail()
        private static final String textEmailValid = "email@domain.co.uk";
    }

    private static final class TestTextItemInvalid {
        @TextEmail()
        private static final String textEmailInvalid = "email@domain";
    }

    private static final class TestTextItemValidInvalid {
        @TextEmail()
        private static final String textEmailValid = "email@domain.co.uk";
        @TextEmail()
        private static final String textEmailInvalid = "bob";
    }

    private static final class TestTextItemValidInvalidDeep {
        @TextEmail()
        private static final String textEmailValid = "email@domain.co.uk";
        private static final TestTextItemInvalid innerInvalidItem = new TestTextItemInvalid();
    }


}