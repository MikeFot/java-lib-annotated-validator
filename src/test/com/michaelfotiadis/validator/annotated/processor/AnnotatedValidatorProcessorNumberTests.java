package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.general.IsNull;
import com.michaelfotiadis.validator.annotated.annotations.general.NotNull;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleMinValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.FloatEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.FloatMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.IntegerEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.IntegerMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.IntegerMinValue;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class AnnotatedValidatorProcessorNumberTests {

    private static final int INTEGER_MIN = 2;
    private static final int INTEGER_MAX = 10;
    private static final int INTEGER_EQUALS = 5;
    private static final double DOUBLE_MAX = 527.4343d;
    private static final double DOUBLE_MIN = -300300002.12312d;
    private static final float FLOAT_EQUALS = -15.17f;

    private AnnotatedValidatorProcessor processor;


    @Before
    public void setUp() throws Exception {
        processor = new AnnotatedValidatorProcessor();
    }

    @Test
    public void integerMinOutOfRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testIntegerMinMax = INTEGER_MIN - 1;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());
        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.INTEGER_OUT_OF_RANGE));
    }

    @Test
    public void integerNullValue() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testIntegerMinMax = null;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.NULL_VALUE));
    }

    @Test
    public void integerNotEquals() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testIntegerEquals = 36;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.INTEGER_OUT_OF_RANGE));
    }

    @Test
    public void allValuesInRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        final ValidationResultsContainer result = processor.validate(item);
        assertTrue(result.isValid());
        final Set<ValidationStatus> statuses1 = result.getFailedStatuses();
        assertEquals(0, statuses1.size());
    }

    @Test
    public void allValuesInRangeForExtendedClass() throws Exception {
        final SampleExtendedNumber item = new SampleExtendedNumber();
        final ValidationResultsContainer result = processor.validate(item);
        assertTrue(result.isValid());
        final Set<ValidationStatus> statuses1 = result.getFailedStatuses();
        assertEquals(0, statuses1.size());
    }

    @Test
    public void doubleMaxValueOutOfRange() throws Exception {
        final SampleNumber item = new SampleNumber();
        item.testDoubleMax = Double.MAX_VALUE;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.DOUBLE_OUT_OF_RANGE));
    }

    @Test
    public void doubleMaxValueOutOfRangeForExtendedClass() throws Exception {
        final SampleExtendedNumber item = new SampleExtendedNumber();
        item.testDoubleMax = Double.MAX_VALUE;
        final ValidationResultsContainer result = processor.validate(item);
        assertFalse(result.isValid());

        final Set<ValidationStatus> statuses = result.getFailedStatuses();
        assertEquals(1, statuses.size());
        assertTrue(statuses.contains(ValidationStatus.DOUBLE_OUT_OF_RANGE));
    }

    @Test
    public void doubleMaxValueInRange() throws Exception {
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
    private class SampleNumber {

        private static final double EPSILON = 0.0000000001d;

        @IntegerMaxValue(INTEGER_MAX)
        @IntegerMinValue(INTEGER_MIN)
        Integer testIntegerMinMax;
        @IntegerEqualsValue(INTEGER_EQUALS)
        int testIntegerEquals;
        @NotNull
        @DoubleMaxValue(max = DOUBLE_MAX, epsilon = EPSILON)
        Double testDoubleMax;
        @DoubleMinValue(min = DOUBLE_MIN, epsilon = EPSILON)
        double testDoubleMin;
        @FloatEqualsValue(value = FLOAT_EQUALS, epsilon = EPSILON)
        float testFloatEquals;
        @FloatMaxValue(max = FLOAT_EQUALS + 1, epsilon = EPSILON)
        Float testFloatMax;
        @SuppressWarnings("unused")
        @IsNull
        Integer testIsNullInteger;

        private SampleNumber() {
            testIntegerMinMax = INTEGER_MAX - INTEGER_MIN + 1;
            testIntegerEquals = INTEGER_EQUALS;
            testDoubleMax = DOUBLE_MAX / 2d;
            testDoubleMin = DOUBLE_MIN + 1;
            testFloatEquals = FLOAT_EQUALS;
            testFloatMax = FLOAT_EQUALS;
        }

    }

}
