package com.michaelfotiadis.validator.pojo.helper;

import com.michaelfotiadis.validator.pojo.common.collection.ContainsNoNullsValidator;
import com.michaelfotiadis.validator.pojo.common.collection.IsNotNullOrEmptyValidator;

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
        return new IsNotNullOrEmptyValidator().validate(collection).isValid();
    }


    public boolean hasNonNulls(final Collection collection) {
        return new ContainsNoNullsValidator().validate(collection).isValid();
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
