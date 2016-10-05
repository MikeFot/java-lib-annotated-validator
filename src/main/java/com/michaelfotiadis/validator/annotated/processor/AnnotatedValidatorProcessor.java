package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.ValidationResultContainer;
import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.parser.AnnotationParser;
import com.michaelfotiadis.validator.annotated.processor.validators.DoubleAnnotationValidator;
import com.michaelfotiadis.validator.annotated.processor.validators.FloatAnnotationValidator;
import com.michaelfotiadis.validator.annotated.processor.validators.IntegerAnnotationValidator;
import com.michaelfotiadis.validator.annotated.processor.validators.ObjectAnnotationValidator;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;
import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AnnotatedValidatorProcessor {

    private static final IntegerAnnotationValidator sIntegerValidator = new IntegerAnnotationValidator();
    private static final DoubleAnnotationValidator sDoubleValidator = new DoubleAnnotationValidator();
    private static final FloatAnnotationValidator sFloatValidator = new FloatAnnotationValidator();
    private static final ObjectAnnotationValidator sObjectValidator = new ObjectAnnotationValidator();

    public <T> ValidationResultContainer validate(final T item) {

        final List<Field> fields = AnnotationParser.getDeclaredFields(item.getClass());

        final ValidationResultContainer result = new ValidationResultContainer();

        for (final Field field : fields) {
            try {
                final List<ValidationResult> results = validateField(item, field);
                if (!AnnotationProcessorUtils.areAllResultsValid(results)) {
                    result.put(getHumanName(item, field), AnnotationProcessorUtils.getFailures(results));
                }
            } catch (final IllegalAccessException e) {
                result.put(getHumanName(item, field), ValidationStatus.EXCEPTION);
            }
        }

        return result;
    }

    private static <T> String getHumanName(final T item, final Field field) {
        return String.format("%s of type %s in object of type %s", field.getName(), field.getType().getName(), item.getClass().getName());
    }

    public <T> List<ValidationResult> validateField(final T item, final Field field) throws IllegalAccessException {

        final Annotation[] annotations = field.getDeclaredAnnotations();
        final List<ValidationResult> results = new ArrayList<>();

        if (annotations != null && annotations.length > 0) {
            for (final Annotation annotation : annotations) {
                final AnnotationCategory category = AnnotationParser.getCategoryOfAnnotation(annotation);
                final ValidationResult result;

                final Object o = field.get(item);
                switch (category) {
                    case UNUSED:
                        result = ValidationResult.success();
                        break;
                    case OBJECT:
                        result = sObjectValidator.validate(o, annotation);
                        break;
                    case INTEGER:
                        result = sIntegerValidator.validate((Integer) o, annotation);
                        break;
                    case DOUBLE:
                        result = sDoubleValidator.validate((Double) o, annotation);
                        break;
                    case FLOAT:
                        result = sFloatValidator.validate((Float) o, annotation);
                        break;
                    default:
                        result = ValidationResult.success();
                }
                results.add(result);
            }

        }

        return results;

    }


}
