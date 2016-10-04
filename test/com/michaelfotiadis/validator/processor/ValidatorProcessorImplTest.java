package com.michaelfotiadis.validator.processor;

import com.michaelfotiadis.validator.Validator;
import com.michaelfotiadis.validator.common.CollectionValidator;
import com.michaelfotiadis.validator.common.ContainsNoNulls;
import com.michaelfotiadis.validator.common.NotEmptyValidator;
import com.michaelfotiadis.validator.common.StringValidator;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 *
 */
public class ValidatorProcessorImplTest {

    private ValidatorProcessor mProcessor;

    @Before
    public void setUp() throws Exception {
        mProcessor = new ValidatorProcessorImpl();
        mProcessor.register(String.class, new NotEmptyValidator());
        mProcessor.register(Collection.class, new ContainsNoNulls());
    }

    @Test
    public void testGetValidator() throws Exception {

        final Validator<String> stringValidator = mProcessor.getValidator(String.class);
        assertNotNull(stringValidator);
        if (!(stringValidator instanceof StringValidator)) {
            fail();
        }

        final Validator<Collection> collectionValidator = mProcessor.getValidator(Collection.class);
        assertNotNull(collectionValidator);
        if (!(collectionValidator instanceof CollectionValidator)) {
            fail();
        }

        mProcessor.clear();
        assertFalse("We should not be able to handle this class after a processor clear", mProcessor.canHandle(String.class));


    }

    @Test
    public void testValidate() throws Exception {

        final Validator<String> validator = mProcessor.getValidator(String.class);

        assertNotNull(validator);

        assertFalse("Should have been invalid", validator.validate(""));
        assertFalse("Should have been invalid", validator.validate(null));
        assertTrue("Should have been valid", validator.validate("ValidString"));
        assertTrue("Should have been valid", validator.validate("anotherValidOne"));
    }

    @Test
    public void testValidateDirect() throws Exception {

        assertFalse("Should have been invalid", mProcessor.validate(""));
        assertFalse("Should have been invalid", mProcessor.validate(null));
        assertTrue("Should have been valid", mProcessor.validate("ValidString"));
        assertTrue("Should have been valid", mProcessor.validate("anotherValidOne"));

    }

}