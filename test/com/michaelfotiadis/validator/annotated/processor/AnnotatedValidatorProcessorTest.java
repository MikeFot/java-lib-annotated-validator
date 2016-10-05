package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.ValidationResultContainer;
import com.michaelfotiadis.validator.annotated.annotations.number.DoubleMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.number.DoubleMinValue;
import com.michaelfotiadis.validator.annotated.annotations.number.IntegerEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.number.IntegerMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.number.IntegerMinValue;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

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
        final ValidationResultContainer result = processor.validate(item);
        assertFalse(result.isValid());
        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.INTEGER_OUT_OF_RANGE));
    }

    @Test
    public void testIntegerNullValue() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testIntegerMinMax = null;
        final ValidationResultContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.NULL_VALUE));
    }

    @Test
    public void testIntegerNotEquals() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testIntegerEquals = 36;
        final ValidationResultContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.INTEGER_OUT_OF_RANGE));
    }

    @Test
    public void testAllValuesInRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        final ValidationResultContainer result = processor.validate(item);
        assertTrue(result.isValid());
        final Set<ValidationStatus> statuses1 = result.getFailedStatuses();
        assertEquals(0, statuses1.size());
    }

    @Test
    public void testAllValuesInRangeForExtendedClass() throws Exception {
        final SampleExtendedNumber item = new SampleExtendedNumber();
        final ValidationResultContainer result = processor.validate(item);
        assertTrue(result.isValid());
        final Set<ValidationStatus> statuses1 = result.getFailedStatuses();
        assertEquals(0, statuses1.size());
    }

    @Test
    public void testDoubleMaxValueOutOfRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testDoubleMax = Double.MAX_VALUE;
        final ValidationResultContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.DOUBLE_OUT_OF_RANGE));
    }

    @Test
    public void testDoubleMaxValueOutOfRangeForExtendedClass() throws Exception {
        final SampleExtendedNumber item = new SampleExtendedNumber();
        item.testDoubleMax = Double.MAX_VALUE;
        final ValidationResultContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.DOUBLE_OUT_OF_RANGE));
    }

    @Test
    public void testDoubleMaxValueInRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testDoubleMax = DOUBLE_MAX / 2d;
        final ValidationResultContainer result = processor.validate(item);
        assertTrue(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(0, statuses.size());
    }

    private class SampleExtendedNumber extends SampleNumber {
        // nothing here
    }

    @SuppressWarnings({"InnerClassMayBeStatic", "ClassWithOnlyPrivateConstructors"})
    private class SampleNumber {
        @IntegerMaxValue(INTEGER_MAX)
        @IntegerMinValue(INTEGER_MIN)
        Integer testIntegerMinMax;
        @IntegerEqualsValue(INTEGER_EQUALS)
        int testIntegerEquals;
        @DoubleMaxValue(DOUBLE_MAX)
        Double testDoubleMax;
        @DoubleMinValue(DOUBLE_MIN)
        double testDoubleMin;

        private SampleNumber() {
            testIntegerMinMax = INTEGER_MAX - INTEGER_MIN + 1;
            testIntegerEquals = INTEGER_EQUALS;
            testDoubleMax = DOUBLE_MAX / 2d;
            testDoubleMin = DOUBLE_MIN + 1;
        }
    }

}