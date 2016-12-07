package com.michaelfotiadis.validator.annotated;

import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.annotations.Category;
import com.michaelfotiadis.validator.annotated.annotations.array.ArrayContainsNoNulls;
import com.michaelfotiadis.validator.annotated.annotations.array.ArrayContainsValue;
import com.michaelfotiadis.validator.annotated.annotations.array.ArrayIsNotEmpty;
import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionContainsNoNulls;
import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionContainsValue;
import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionIsNotEmpty;
import com.michaelfotiadis.validator.annotated.annotations.conditional.BooleanEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.general.IsNull;
import com.michaelfotiadis.validator.annotated.annotations.general.NotNull;
import com.michaelfotiadis.validator.annotated.annotations.numeric.bytenum.ByteEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.doublenum.DoubleMinValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.FloatEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.FloatMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.floatnum.FloatMinValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.IntegerEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.IntegerMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.integernum.IntegerMinValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.shortnum.ShortEqualsValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.shortnum.ShortMaxValue;
import com.michaelfotiadis.validator.annotated.annotations.numeric.shortnum.ShortMinValue;
import com.michaelfotiadis.validator.annotated.annotations.text.TextDateFormat;
import com.michaelfotiadis.validator.annotated.annotations.text.TextEmail;
import com.michaelfotiadis.validator.annotated.annotations.text.TextExactLength;
import com.michaelfotiadis.validator.annotated.annotations.text.TextIsNumeric;
import com.michaelfotiadis.validator.annotated.annotations.text.TextMatchesExpression;
import com.michaelfotiadis.validator.annotated.annotations.text.TextMaxLength;
import com.michaelfotiadis.validator.annotated.annotations.text.TextMinLength;
import com.michaelfotiadis.validator.annotated.annotations.text.TextNotNullOrEmpty;
import com.michaelfotiadis.validator.annotated.annotations.text.TextUrl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class SupportedAnnotationContainerTest {

    @Test
    public void isSupported() throws Exception {

        assertFalse(SupportedAnnotationContainer.isSupported(SupportedAnnotationContainer.class));

        // collection annotations
        checkIsSupported(CollectionContainsNoNulls.class);
        checkIsSupported(CollectionContainsValue.class);
        checkIsSupported(CollectionIsNotEmpty.class);

        // array annotations
        checkIsSupported(ArrayContainsValue.class);
        checkIsSupported(ArrayIsNotEmpty.class);
        checkIsSupported(ArrayContainsNoNulls.class);

        // boolean annotations
        checkIsSupported(BooleanEqualsValue.class);

        // general annotations
        checkIsSupported(IsNull.class);
        checkIsSupported(NotNull.class);

        // Byte annotations
        checkIsSupported(ByteEqualsValue.class);

        // Integer annotations
        checkIsSupported(IntegerMinValue.class);
        checkIsSupported(IntegerMaxValue.class);
        checkIsSupported(IntegerEqualsValue.class);

        // Double annotations
        checkIsSupported(DoubleMinValue.class);
        checkIsSupported(DoubleMaxValue.class);
        checkIsSupported(DoubleEqualsValue.class);

        // Float annotations
        checkIsSupported(FloatMinValue.class);
        checkIsSupported(FloatMaxValue.class);
        checkIsSupported(FloatEqualsValue.class);

        // Short annotations
        checkIsSupported(ShortMinValue.class);
        checkIsSupported(ShortMaxValue.class);
        checkIsSupported(ShortEqualsValue.class);

        // String annotations
        checkIsSupported(TextDateFormat.class);
        checkIsSupported(TextEmail.class);
        checkIsSupported(TextExactLength.class);
        checkIsSupported(TextIsNumeric.class);
        checkIsSupported(TextMatchesExpression.class);
        checkIsSupported(TextMaxLength.class);
        checkIsSupported(TextMinLength.class);
        checkIsSupported(TextNotNullOrEmpty.class);
        checkIsSupported(TextUrl.class);


    }

    @Test
    public void isCorrectCategory() throws Exception {

        // collection annotations
        checkCategory(CollectionContainsNoNulls.class, AnnotationCategory.COLLECTION);
        checkCategory(CollectionContainsValue.class, AnnotationCategory.COLLECTION);
        checkCategory(CollectionIsNotEmpty.class, AnnotationCategory.COLLECTION);

        // array annotations
        checkCategory(ArrayContainsValue.class, AnnotationCategory.ARRAY);
        checkCategory(ArrayIsNotEmpty.class, AnnotationCategory.ARRAY);
        checkCategory(ArrayContainsNoNulls.class, AnnotationCategory.ARRAY);

        // boolean annotations
        checkCategory(BooleanEqualsValue.class, AnnotationCategory.BOOLEAN);

        // general annotations
        checkCategory(IsNull.class, AnnotationCategory.GENERAL);
        checkCategory(NotNull.class, AnnotationCategory.GENERAL);

        // Byte annotations
        checkCategory(ByteEqualsValue.class, AnnotationCategory.BYTE);

        // Integer annotations
        checkCategory(IntegerMinValue.class, AnnotationCategory.INTEGER);
        checkCategory(IntegerMaxValue.class, AnnotationCategory.INTEGER);
        checkCategory(IntegerEqualsValue.class, AnnotationCategory.INTEGER);

        // Double annotations
        checkCategory(DoubleMinValue.class, AnnotationCategory.DOUBLE);
        checkCategory(DoubleMaxValue.class, AnnotationCategory.DOUBLE);
        checkCategory(DoubleEqualsValue.class, AnnotationCategory.DOUBLE);

        // Float annotations
        checkCategory(FloatMinValue.class, AnnotationCategory.FLOAT);
        checkCategory(FloatMaxValue.class, AnnotationCategory.FLOAT);
        checkCategory(FloatEqualsValue.class, AnnotationCategory.FLOAT);

        // Short annotations
        checkCategory(ShortMinValue.class, AnnotationCategory.SHORT);
        checkCategory(ShortMaxValue.class, AnnotationCategory.SHORT);
        checkCategory(ShortEqualsValue.class, AnnotationCategory.SHORT);

        // String annotations
        checkCategory(TextDateFormat.class, AnnotationCategory.STRING);
        checkCategory(TextEmail.class, AnnotationCategory.STRING);
        checkCategory(TextExactLength.class, AnnotationCategory.STRING);
        checkCategory(TextIsNumeric.class, AnnotationCategory.STRING);
        checkCategory(TextMatchesExpression.class, AnnotationCategory.STRING);
        checkCategory(TextMaxLength.class, AnnotationCategory.STRING);
        checkCategory(TextMinLength.class, AnnotationCategory.STRING);
        checkCategory(TextNotNullOrEmpty.class, AnnotationCategory.STRING);
        checkCategory(TextUrl.class, AnnotationCategory.STRING);

    }

    @Test
    public void getSupportedClasses() throws Exception {
        assertNotEquals(0, SupportedAnnotationContainer.getSupportedClasses());
    }

    private static void checkCategory(final Class clazz, final AnnotationCategory annotationCategory) {

        final Category category = (Category) clazz.getAnnotation(Category.class);

        assertEquals(annotationCategory, category.type());
    }

    private static void checkIsSupported(final Class clazz) {
        assertTrue(isSupported(clazz));
    }

    private static boolean isSupported(final Class clazz) {
        return SupportedAnnotationContainer.isSupported(clazz);
    }

}