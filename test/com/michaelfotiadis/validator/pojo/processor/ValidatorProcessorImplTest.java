package com.michaelfotiadis.validator.pojo.processor;

import com.michaelfotiadis.validator.pojo.Validator;
import com.michaelfotiadis.validator.pojo.common.collection.CollectionValidator;
import com.michaelfotiadis.validator.pojo.common.collection.ContainsNoNullsValidator;
import com.michaelfotiadis.validator.pojo.common.text.NotEmptyValidator;
import com.michaelfotiadis.validator.pojo.common.text.StringValidator;

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
        mProcessor.register(Collection.class, new ContainsNoNullsValidator());
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

        assertFalse("Should have been invalid", validator.validate("").isValid());
        assertFalse("Should have been invalid", validator.validate(null).isValid());
        assertTrue("Should have been valid", validator.validate("ValidString").isValid());
        assertTrue("Should have been valid", validator.validate("anotherValidOne").isValid());
    }

    @Test
    public void testValidateDirect() throws Exception {

        assertFalse("Should have been invalid", mProcessor.validate(""));
        assertFalse("Should have been invalid", mProcessor.validate(null));
        assertTrue("Should have been valid", mProcessor.validate("ValidString"));
        assertTrue("Should have been valid", mProcessor.validate("anotherValidOne"));

    }

}