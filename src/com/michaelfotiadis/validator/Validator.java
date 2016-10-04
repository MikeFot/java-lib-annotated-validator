package com.michaelfotiadis.validator;


/**
 *
 */
public interface Validator<T> {
    boolean validate(T item);
}
