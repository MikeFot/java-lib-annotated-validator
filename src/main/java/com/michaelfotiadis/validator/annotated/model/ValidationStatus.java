package com.michaelfotiadis.validator.annotated.model;

/**
 *
 */
public enum ValidationStatus {

    UNDEFINED_FAILURE,
    NULL_VALUE,
    EMPTY_COLLECTION,
    EMPTY_STRING,
    INVALID_VALUE,
    VALUE_OUT_OF_RANGE,
    NUMBER_OUT_OF_RANGE,
    INTEGER_OUT_OF_RANGE,
    DOUBLE_OUT_OF_RANGE,
    FLOAT_OUT_OF_RANGE,
    EXCEPTION,
    SUCCESS

}