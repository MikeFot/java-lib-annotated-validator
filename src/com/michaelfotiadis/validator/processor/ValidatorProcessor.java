package com.michaelfotiadis.validator.processor;

import com.michaelfotiadis.validator.Validator;

/**
 *
 */
public interface ValidatorProcessor {

    <T> Validator<T> getValidator(Class<?> clazz);

    boolean canHandle(Class<?> clazz);

    <T> boolean validate(T item);

    <T> void register(Class<T> clazz, Validator<T> validator);

    void clear();

}
