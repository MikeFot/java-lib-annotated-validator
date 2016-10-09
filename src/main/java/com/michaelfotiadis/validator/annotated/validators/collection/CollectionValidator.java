package com.michaelfotiadis.validator.annotated.validators.collection;

import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionContainsNoNulls;
import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionContainsValue;
import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionIsNotEmpty;
import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;
import com.michaelfotiadis.validator.annotated.validators.Validator;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 */
public class CollectionValidator implements Validator<Collection<?>> {

    @Override
    public ValidationResult validate(final Collection<?> collection, final Annotation annotation) {

        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(CollectionIsNotEmpty.class)) {
            return handleIsNotEmpty(collection);
        } else if (type.equals(CollectionContainsNoNulls.class)) {
            return handleContainsNoNulls(collection);
        } else if (type.equals(CollectionContainsValue.class)) {
            return handleContainsValue(collection, ((CollectionContainsValue) annotation).value());
        } else {
            return ValidationResult.failure();
        }

    }

    private static ValidationResult handleContainsValue(final Collection<?> collection,
                                                        final Class<?> value) {

        final ValidationResult isNotEmpty = handleIsNotEmpty(collection);

        if (isNotEmpty.isValid()) {

            // get the class of the first item
            Class genericClass = null;
            final Iterator it = collection.iterator();
            if (it.hasNext()) {
                genericClass = it.next().getClass();
            }

            if (genericClass != null) {
                // This is considered a failure if the two classes do not match
                if (value.getClass().equals(genericClass)) {
                    if (collection.contains(value)) {
                        return ValidationResult.success();
                    } else {
                        return new ValidationResult(ValidationStatus.INVALID_VALUE);
                    }
                } else {
                    return new ValidationResult(ValidationStatus.WRONG_TYPE_ARGUMENTS);
                }
            } else {
                return new ValidationResult(ValidationStatus.EXCEPTION);
            }
        } else {
            return isNotEmpty;
        }
    }

    private static ValidationResult handleContainsNoNulls(final Collection<?> collection) {

        if (collection == null) {
            return ValidationResult.nullValue();
        } else {
            for (final Object item : collection) {
                if (item == null) {
                    return new ValidationResult(ValidationStatus.COLLECTION_CONTAINS_NULL);
                }
            }
            return ValidationResult.success();
        }

    }

    private static ValidationResult handleIsNotEmpty(final Collection<?> collection) {

        if (collection == null) {
            return ValidationResult.nullValue();
        } else if (collection.isEmpty()) {
            return new ValidationResult(ValidationStatus.EMPTY_COLLECTION);
        } else {
            return ValidationResult.success();
        }

    }
}
