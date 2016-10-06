package com.michaelfotiadis.validator.annotated;

import com.michaelfotiadis.validator.annotated.model.ValidationStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class ValidationResultsContainer {

    private final Map<String, List<ValidationStatus>> resultsMap;

    public ValidationResultsContainer() {
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
        resultsMap.put(key, Collections.singletonList(status));
    }

    public Set<String> getFailedFieldNames() {
        return getResults().keySet();
    }

    public Set<ValidationStatus> getFailedStatuses() {

        final Set<ValidationStatus> statuses = new TreeSet<>();

        for (final List<ValidationStatus> list : getResults().values()) {
            statuses.addAll(list);
        }

        return statuses;
    }

}
