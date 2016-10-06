package com.michaelfotiadis.validator.annotated.processor;

import com.michaelfotiadis.validator.annotated.model.ValidationResult;
import com.michaelfotiadis.validator.annotated.model.ValidationStatus;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class AnnotationProcessorUtils {

    private AnnotationProcessorUtils() {
        // NOOP
    }

    public static List<ValidationStatus> getFailures(final List<ValidationResult> results) {

        final List<ValidationStatus> statuses = new ArrayList<>();

        for (final ValidationResult result : results) {
            // fail if even one of the results is invalid
            if (!result.getStatus().equals(ValidationStatus.SUCCESS)) {
                statuses.add(result.getStatus());
            }
        }
        return statuses;

    }

    public static boolean areAllResultsValid(final List<ValidationResult> results) {
        for (final ValidationResult result : results) {
            // fail if even one of the results is invalid
            if (!result.isValid()) {
                return false;
            }
        }
        return true;
    }

}
