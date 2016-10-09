package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.ValidationResultsContainer;
import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.parser.AnnotationParser;
import com.michaelfotiadis.validator.annotated.validators.Validator;
import com.michaelfotiadis.validator.annotated.validators.collection.CollectionValidator;
import com.michaelfotiadis.validator.annotated.validators.conditional.BooleanValidator;
import com.michaelfotiadis.validator.annotated.validators.general.ObjectValidator;
import com.michaelfotiadis.validator.annotated.validators.numeric.ByteValidator;
import com.michaelfotiadis.validator.annotated.validators.numeric.DoubleValidator;
import com.michaelfotiadis.validator.annotated.validators.numeric.FloatValidator;
import com.michaelfotiadis.validator.annotated.validators.numeric.IntegerValidator;
import com.michaelfotiadis.validator.annotated.validators.numeric.ShortValidator;
import com.michaelfotiadis.validator.annotated.validators.text.StringValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@SuppressWarnings("WeakerAccess")
public final class AnnotatedValidatorProcessor {

    private final Map<AnnotationCategory, Validator<?>> mValidatorMap;

    @SuppressWarnings("WeakerAccess")
    public AnnotatedValidatorProcessor() {

        mValidatorMap = new HashMap<>();
        mValidatorMap.put(AnnotationCategory.GENERAL, new ObjectValidator());
        mValidatorMap.put(AnnotationCategory.BYTE, new ByteValidator());
        mValidatorMap.put(AnnotationCategory.INTEGER, new IntegerValidator());
        mValidatorMap.put(AnnotationCategory.SHORT, new ShortValidator());
        mValidatorMap.put(AnnotationCategory.FLOAT, new FloatValidator());
        mValidatorMap.put(AnnotationCategory.DOUBLE, new DoubleValidator());
        mValidatorMap.put(AnnotationCategory.STRING, new StringValidator());
        mValidatorMap.put(AnnotationCategory.BOOLEAN, new BooleanValidator());
        mValidatorMap.put(AnnotationCategory.COLLECTION, new CollectionValidator());

    }

    public <T> ValidationResultsContainer validate(final T item) {

        final List<Field> fields = AnnotationParser.getDeclaredFields(item.getClass());

        final ValidationResultsContainer result = new ValidationResultsContainer();

        for (final Field field : fields) {
            try {
                field.setAccessible(true);
                final List<ValidationResult> results = validateField(item, field);
                if (!AnnotationProcessorUtils.areAllResultsValid(results)) {
                    result.put(getHumanName(item, field), AnnotationProcessorUtils.getFailures(results));
                }
            } catch (final IllegalAccessException e) {
                result.put(getHumanName(item, field), ValidationStatus.EXCEPTION);
            } finally {
                field.setAccessible(false);
            }
        }

        return result;
    }

    /**
     * Used for <b>testing</b> if all validators have been added to the map.
     * The {@link AnnotationCategory#UNUSED} annotation will return true by default.
     *
     * @param annotationCategory {@link AnnotationCategory} annotation category value
     * @return true if supported and validator is not null for category
     */
    /*package*/ boolean canSupportAnnotationCategory(final AnnotationCategory annotationCategory) {

        //noinspection SimplifiableIfStatement
        if (annotationCategory.equals(AnnotationCategory.UNUSED)) {
            return true;
        } else {
            return mValidatorMap.containsKey(annotationCategory)
                    && mValidatorMap.get(annotationCategory) != null;
        }
    }

    private <T> List<ValidationResult> validateField(final T item, final Field field) throws IllegalAccessException {

        final Annotation[] annotations = field.getDeclaredAnnotations();
        final List<ValidationResult> results = new ArrayList<>();

        if (annotations != null && annotations.length > 0) {
            for (final Annotation annotation : annotations) {
                final AnnotationCategory category = AnnotationParser.getCategoryOfAnnotation(annotation);
                final ValidationResult result;

                final Object o = field.get(item);

                if (mValidatorMap.containsKey(category)) {

                    final Validator validator = mValidatorMap.get(category);
                    //noinspection unchecked
                    result = validator.validate(o, annotation);
                } else {
                    result = ValidationResult.success();
                }

                results.add(result);
            }

        }

        return results;

    }

    private static <T> String getHumanName(final T item, final Field field) {
        return String.format("%s of type %s in object of type %s",
                field.getName(),
                field.getType().getName(),
                item.getClass().getName());
    }

}
