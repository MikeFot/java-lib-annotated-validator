package com.michaelfotiadis.validator.pojo.common.collection;

import com.michaelfotiadis.validator.pojo.Validator;

import java.util.Collection;

/**
 *
 */
public interface CollectionValidator extends Validator<Collection> {

    boolean validate(final Collection container);

}
