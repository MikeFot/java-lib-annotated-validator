package com.michaelfotiadis.validator.pojo.common.collection;

import com.michaelfotiadis.validator.pojo.Validator;
import com.michaelfotiadis.validator.pojo.results.ValidationResult;

import java.util.Collection;

/**
 *
 */
public interface CollectionValidator extends Validator<Collection> {

    ValidationResult validate(final Collection container);

}
