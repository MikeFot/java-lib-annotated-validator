package com.michaelfotiadis.validator.pojo;


/**
 *
 */
public interface Validator<T> {
    boolean validate(T item);
}
