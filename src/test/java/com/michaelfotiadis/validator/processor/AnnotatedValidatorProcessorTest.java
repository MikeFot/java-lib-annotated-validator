package com.michaelfotiadis.validator.processor;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.ValDoubleMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.ValDoubleMinValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.ValIntegerEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.ValIntegerMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.ValIntegerMinValue;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.processor.AnnotatedValidatorProcessor;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class AnnotatedValidatorProcessorTest {

    private AnnotatedValidatorProcessor processor;

    private static final int INTEGER_MIN = 2;
    private static final int INTEGER_MAX = 10;
    private static final int INTEGER_EQUALS = 5;
    private static final double DOUBLE_MAX = 527.4343d;
    private static final double DOUBLE_MIN = -300300002.12312d;

    @Before
    public void setUp() throws Exception {
        processor = new AnnotatedValidatorProcessor();
    }

    @Test
    public void testIntegerMinOutOfRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testIntegerMinMax = INTEGER_MIN - 1;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());
        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.INTEGER_OUT_OF_RANGE));
    }

    @Test
    public void testIntegerNullValue() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testIntegerMinMax = null;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.NULL_VALUE));
    }

    @Test
    public void testIntegerNotEquals() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testIntegerEquals = 36;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.INTEGER_OUT_OF_RANGE));
    }

    @Test
    public void testAllValuesInRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        final ValidationResultsContainer result = processor.validate(item);
        assertTrue(result.isValid());
        final Set<ValidationStatus> statuses1 = result.getFailedStatuses();
        assertEquals(0, statuses1.size());
    }

    @Test
    public void testAllValuesInRangeForExtendedClass() throws Exception {
        final SampleExtendedNumber item = new SampleExtendedNumber();
        final ValidationResultsContainer result = processor.validate(item);
        assertTrue(result.isValid());
        final Set<ValidationStatus> statuses1 = result.getFailedStatuses();
        assertEquals(0, statuses1.size());
    }

    @Test
    public void testDoubleMaxValueOutOfRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testDoubleMax = Double.MAX_VALUE;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.DOUBLE_OUT_OF_RANGE));
    }

    @Test
    public void testDoubleMaxValueOutOfRangeForExtendedClass() throws Exception {
        final SampleExtendedNumber item = new SampleExtendedNumber();
        item.testDoubleMax = Double.MAX_VALUE;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.DOUBLE_OUT_OF_RANGE));
    }

    @Test
    public void testDoubleMaxValueInRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testDoubleMax = DOUBLE_MAX / 2d;
        final ValidationResultsContainer result = processor.validate(item);
        assertTrue(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(0, statuses.size());
    }

    private class SampleExtendedNumber extends SampleNumber {
        // nothing here
    }

    @SuppressWarnings({"InnerClassMayBeStatic", "ClassWithOnlyPrivateConstructors"})
    public class SampleNumber {
        @ValIntegerMaxValue(INTEGER_MAX)
        @ValIntegerMinValue(INTEGER_MIN)
        Integer testIntegerMinMax;
        @ValIntegerEqualsValue(INTEGER_EQUALS)
        int testIntegerEquals;
        @ValDoubleMaxValue(DOUBLE_MAX)
        Double testDoubleMax;
        @ValDoubleMinValue(DOUBLE_MIN)
        double testDoubleMin;

        private SampleNumber() {
            testIntegerMinMax = INTEGER_MAX - INTEGER_MIN + 1;
            testIntegerEquals = INTEGER_EQUALS;
            testDoubleMax = DOUBLE_MAX / 2d;
            testDoubleMin = DOUBLE_MIN + 1;
        }
    }

}