package com.michaelfotiadis.validator.helper;

/**
 *
 */
public final class ValidationHelper {

    private static final CollectionValidationHelper COLLECTION_HELPER = new CollectionValidationHelper();
    private static final StringValidationHelper STRING_HELPER = new StringValidationHelper();

    private ValidationHelper() {
        // DO NOT INSTANTIATE
    }

    public static CollectionValidationHelper getCollectionHelper() {
        return COLLECTION_HELPER;
    }

    public static StringValidationHelper getStringHelper() {
        return STRING_HELPER;
    }


}
