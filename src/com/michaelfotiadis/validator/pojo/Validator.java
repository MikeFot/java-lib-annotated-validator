package com.michaelfotiadis.validator.pojo;


import com.michaelfotiadis.validator.pojo.results.ValidationResult;

/**
 *
 */
public interface Validator<T> {

    ValidationResult validate(T item);

}
