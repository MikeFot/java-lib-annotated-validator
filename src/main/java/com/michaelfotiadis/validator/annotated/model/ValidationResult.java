package com.michaelfotiadis.validator.annotated.model;

/**
 *
 */
public class ValidationResult {

    private final ValidationStatus status;

    public ValidationResult(final ValidationStatus status) {
        this.status = status;
    }

    public ValidationResult(final boolean isValid) {
        if (isValid) {
            this.status = ValidationStatus.SUCCESS;
        } else {
            this.status = ValidationStatus.UNDEFINED_FAILURE;
        }
    }

    /**
     * Static factory
     *
     * @return successful {@link ValidationResult}
     */
    public static ValidationResult success() {
        return new ValidationResult(ValidationStatus.SUCCESS);
    }

    /**
     * Static factory
     *
     * @return failed {@link ValidationResult}
     */
    public static ValidationResult failure() {
        return new ValidationResult(ValidationStatus.UNDEFINED_FAILURE);
    }

    /**
     * Static factory
     *
     * @return null value {@link ValidationResult}
     */
    public static ValidationResult nullValue() {
        return new ValidationResult(ValidationStatus.NULL_VALUE);
    }

    public ValidationStatus getStatus() {
        return this.status;
    }

    public boolean isValid() {
        return this.status == ValidationStatus.SUCCESS;
    }

}
