package com.michaelfotiadis.validator.annotated;

import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ValidationMapResult {

    private final Map<String, List<ValidationStatus>> resultsMap;

    public ValidationMapResult() {
        resultsMap = new HashMap<>();
    }

    public Map<String, List<ValidationStatus>> getResults() {
        return resultsMap;
    }

    public boolean isValid() {
        return resultsMap.size() == 0;
    }

    public void put(final String key, final List<ValidationStatus> statuses) {
        resultsMap.put(key, statuses);
    }

    public void put(final String key, final ValidationStatus status) {
        final List<ValidationStatus> exceptionList = new ArrayList<>();
        exceptionList.add(status);
        resultsMap.put(key, exceptionList);
    }

}
