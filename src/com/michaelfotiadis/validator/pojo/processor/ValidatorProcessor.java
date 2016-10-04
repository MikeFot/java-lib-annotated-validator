package com.michaelfotiadis.validator.pojo.processor;

import com.michaelfotiadis.validator.pojo.Validator;

/**
 *
 */
public interface ValidatorProcessor {

    <T> Validator<T> getValidator(Class<?> clazz);

    boolean canHandle(Class<?> clazz);

    <T> boolean validate(T item);

    void register(Class<?> clazz, Validator<?> validator);

    void clear();

}
