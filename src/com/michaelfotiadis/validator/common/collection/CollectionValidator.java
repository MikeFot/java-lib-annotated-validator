package com.michaelfotiadis.validator.common.collection;

import com.michaelfotiadis.validator.Validator;

import java.util.Collection;

/**
 *
 */
public interface CollectionValidator extends Validator<Collection> {

    boolean validate(final Collection container);

}
