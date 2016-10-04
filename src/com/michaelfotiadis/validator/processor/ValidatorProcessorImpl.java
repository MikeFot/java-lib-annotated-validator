package com.michaelfotiadis.validator.processor;

import com.michaelfotiadis.validator.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public final class ValidatorProcessorImpl implements ValidatorProcessor {

    private final Map<Class<?>, Validator> mValidators;

    public ValidatorProcessorImpl() {
        mValidators = new HashMap<>();
    }

    @Override
    public <T> Validator<T> getValidator(final Class<?> clazz) {
        //noinspection unchecked
        return mValidators.get(clazz);
    }

    @Override
    public boolean canHandle(final Class<?> clazz) {
        return mValidators.containsKey(clazz);
    }

    @Override
    public <T> boolean validate(final T item) {
        if (item == null) {
            return false;
        } else if (canHandle(item.getClass())) {
            return getValidator(item.getClass()).validate(item);
        } else {
            throw new UnsupportedOperationException("No validator registered for item " + item.getClass());
        }
    }

    @Override
    public void register(final Class<?> clazz, final Validator<?> validator) {
        mValidators.put(clazz, validator);
    }

    @Override
    public void clear() {
        mValidators.clear();
    }

}
