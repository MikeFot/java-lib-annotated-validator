package com.michaelfotiadis.validator.helper;

import com.michaelfotiadis.validator.common.collection.ContainsNoNullsValidator;
import com.michaelfotiadis.validator.common.collection.IsNotNullOrEmptyValidator;

import java.util.Collection;

/**
 *
 */
@SuppressWarnings("MethodMayBeStatic")
public final class CollectionValidationHelper extends CommonObjectValidationHelper {

    /*package*/ CollectionValidationHelper() {
        // do not instantiate
    }

    public boolean isNotNullOrEmpty(final Collection collection) {
        return new IsNotNullOrEmptyValidator().validate(collection);
    }


    public boolean hasNonNulls(final Collection collection) {
        return new ContainsNoNullsValidator().validate(collection);
    }

    public boolean isPopulatedAndHasNoNulls(final Collection collection) {
        //noinspection SimplifiableIfStatement
        if (isNotNullOrEmpty(collection)) {
            return hasNonNulls(collection);
        } else {
            return false;
        }
    }

}
