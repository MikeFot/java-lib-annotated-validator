package com.michaelfotiadis.validator.annotated.validators.numeric.doublenum;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleMaxValue;
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
public class ProcessorDoubleMaxValueValidatorTest {

    private AnnotatedValidatorProcessor processor;


    @Before
    public void setUp() throws Exception {
        processor = new AnnotatedValidatorProcessor();
    }

    @Test
    public void validateDatesValid() throws Exception {

        final TestDoubleItemValid item = new TestDoubleItemValid();

        final ValidationResultsContainer result = processor.validate(item);

        assertTrue(result.isValid());

    }

    @Test
    public void validateDatesInvalid() throws Exception {

        final TestDoubleItemInvalid item = new TestDoubleItemInvalid();

        final ValidationResultsContainer result = processor.validate(item);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());

        assertTrue(result.getFailedFieldNames().iterator().next().contains("invalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.DOUBLE_OUT_OF_RANGE);
    }

    @Test
    public void validateDatesValidInvalidFailFast() throws Exception {

        final TestDoubleItemValidInvalid item = new TestDoubleItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("invalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.NULL_VALUE);

    }

    @Test
    public void validateDatesValidInvalid() throws Exception {

        final TestDoubleItemValidInvalid item = new TestDoubleItemValidInvalid();

        final ValidationResultsContainer result = processor.validate(item, SearchPolicy.SHALLOW, FailPolicy.CONTINUE);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("invalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.NULL_VALUE);

    }

    @Test
    public void validateDatesValidDeepInvalidShallow() throws Exception {

        final TestDoubleItemValidDeepInvalid item = new TestDoubleItemValidDeepInvalid();

        final ValidationResultsContainer result = processor.validate(item, SearchPolicy.SHALLOW, FailPolicy.CONTINUE);

        assertTrue(result.isValid());

    }

    @Test
    public void validateDatesValidDeepInvalidDeep() throws Exception {

        final TestDoubleItemValidDeepInvalid item = new TestDoubleItemValidDeepInvalid();

        final ValidationResultsContainer result = processor.validate(item, SearchPolicy.DEEP, FailPolicy.CONTINUE);

        assertFalse(result.isValid());
        assertEquals(1, result.getFailedStatuses().size());
        assertTrue(result.getFailedFieldNames().iterator().next().contains("invalid"));
        assertEquals(result.getFailedStatuses().iterator().next(), ValidationStatus.DOUBLE_OUT_OF_RANGE);

    }

    private static final double EPSILON = 0.000000000000000000001d;

    @SuppressWarnings("unused")
    private static final class TestDoubleItemValid {
        @DoubleMaxValue(max = 7d, epsilon = EPSILON)
        private static final double valid = 4.4343d;
    }

    @SuppressWarnings("unused")
    private static final class TestDoubleItemInvalid {
        @DoubleMaxValue(max = 7d, epsilon = EPSILON)
        private static final double invalid = Double.MAX_VALUE;
    }

    @SuppressWarnings("unused")
    private static final class TestDoubleItemValidInvalid {
        @DoubleMaxValue(max = 0.00000000000000000001d, epsilon = EPSILON)
        private static final Double valid = 0.000000000000000000005d;
        @DoubleMaxValue(max = 7d, epsilon = EPSILON)
        private static final Double invalid = null;
    }

    @SuppressWarnings("unused")
    private static final class TestDoubleItemValidDeepInvalid {

        @DoubleMaxValue(max = 65654.4343112312d, epsilon = EPSILON)
        private static final double valid = 65654.4343112312;
        private static final TestDoubleItemInvalid invalid = new TestDoubleItemInvalid();

    }

}