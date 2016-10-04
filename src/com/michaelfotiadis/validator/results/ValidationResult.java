package com.michaelfotiadis.validator.results;

/**
 *
 */
public class ValidationResult {

    private final String mError;
    private final boolean mIsValid;

    public ValidationResult(final boolean isValid, final String error) {
        this.mError = error;
        this.mIsValid = isValid;
    }

    public ValidationResult(final boolean isValid) {
        this.mError = null;
        this.mIsValid = isValid;
    }

    public String getError() {
        return mError;
    }

    public boolean isValid() {
        return mIsValid;
    }
}
