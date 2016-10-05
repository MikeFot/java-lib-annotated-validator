package com.michaelfotiadis.validator.annotated;

import com.michaelfotiadis.validator.pojo.results.ValidationStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class ValidationResultContainer {

    private final Map<String, List<ValidationStatus>> resultsMap;

    public ValidationResultContainer() {
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

    public Set<ValidationStatus> getFailedStatuses() {

        final Set<ValidationStatus> statuses = new TreeSet<>();

        for (final List<ValidationStatus> list : getResults().values()) {
            statuses.addAll(list);
        }

        return statuses;
    }

}
