package com.michaelfotiadis.validator.annotated.validators.numeric.bytenum;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.numeric.bytenum.ByteEqualsValue;
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
public class ProcessorByteEqualsValueValidatorTest {

    private AnnotatedValidatorProcessor processor;


    @Before
    public void setUp() throws Exception {
        processor = new AnnotatedValidatorProcessor();
    }

    @Test
    public void validateDatesValid() throws Exception {

        final TestByteItemValid item = new TestByteItemValid();

        final ValidationResultsContainer result = processor.validate(item);

        assertTrue(result.isValid());

    }

    @Test
    public void validateDatesInvalid() throws Exception {

        final TestByteItemInvalid item = new TestByteItemInvalid();

        final ValidationResultsContainer result = processor.validate(item);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());

        assertTrue(result.getFailedFieldNames().iterator().next().contains("invalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.BYTE_OUT_OF_RANGE);
    }

    @Test
    public void validateDatesValidInvalidFailFast() throws Exception {

        final TestByteItemValidInvalid item = new TestByteItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("invalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.NULL_VALUE);

    }

    @Test
    public void validateDatesValidInvalid() throws Exception {

        final TestByteItemValidInvalid item = new TestByteItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item, SearchPolicy.SHALLOW, FailPolicy.CONTINUE);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("invalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.NULL_VALUE);

    }

    @Test
    public void validateDatesValidDeepInvalidShallow() throws Exception {

        final TestByteItemValidDeepInvalid item = new TestByteItemValidDeepInvalid();

        final ValidationResultsContainer result = processor.validate(item, SearchPolicy.SHALLOW, FailPolicy.CONTINUE);

        assertTrue(result.isValid());

    }

    @Test
    public void validateDatesValidDeepInvalidDeep() throws Exception {

        final TestByteItemValidDeepInvalid item = new TestByteItemValidDeepInvalid();

        final ValidationResultsContainer result = processor.validate(item, SearchPolicy.DEEP, FailPolicy.CONTINUE);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("invalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.BYTE_OUT_OF_RANGE);

    }

    @SuppressWarnings("unused")
    private static final class TestByteItemValid {
        @ByteEqualsValue(value = -1)
        private static final byte valid = -1;
    }

    @SuppressWarnings("unused")
    private static final class TestByteItemInvalid {
        @ByteEqualsValue(value = 0)
        private static final byte invalid = -23;
    }

    @SuppressWarnings("unused")
    private static final class TestByteItemValidInvalid {
        @ByteEqualsValue(value = 0)
        private static final Byte valid = 0;
        @ByteEqualsValue(value = 0)
        private static final Byte invalid = null;
    }

    @SuppressWarnings("unused")
    private static final class TestByteItemValidDeepInvalid {

        @ByteEqualsValue(value = 0)
        private static final byte valid = 0;
        private static final TestByteItemInvalid invalid = new TestByteItemInvalid();

    }

}