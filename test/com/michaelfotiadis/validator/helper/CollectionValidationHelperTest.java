package com.michaelfotiadis.validator.helper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class CollectionValidationHelperTest {

    private CollectionValidationHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new CollectionValidationHelper();
    }

    @Test
    public void isCollectionNotNullOrEmpty() throws Exception {

        assertFalse("Null collection should be failing", helper.isNotNullOrEmpty(null));

        assertFalse("Empty collection should be failing", helper.isNotNullOrEmpty(Collections.emptyList()));

        final List<String> list = new ArrayList<>();
        list.add("test");
        assertTrue("Not null or empty collection should be passing", helper.isNotNull(list));

    }

    @Test
    public void isCollectionNotNull() throws Exception {

        assertFalse("Null collection should be failing", helper.isNotNull(null));

        assertTrue("Empty collection should be passing", helper.isNotNull(Collections.emptyList()));

        final List<String> list = new ArrayList<>();
        list.add("test");
        assertTrue("Not null or empty collection should be passing", helper.isNotNull(list));

    }

    @Test
    public void isCollectionWithNoNulls() throws Exception {

        assertFalse("Null collection should be failing", helper.hasNonNulls(null));

        assertTrue("Empty collection does not count as having null items", helper.hasNonNulls(Collections.emptyList()));

        final List<String> list = new ArrayList<>();
        list.add("test");
        assertTrue("Not null or empty collection should be passing", helper.hasNonNulls(list));

        list.add(null);
        assertFalse("Collection has a null object, it should be failing", helper.hasNonNulls(list));
    }

    @Test
    public void isCollectionPopulatedAndWithNoNulls() throws Exception {
        assertFalse("Null collection should be failing", helper.isPopulatedAndHasNoNulls(null));

        assertFalse("Empty collection should be failing", helper.isPopulatedAndHasNoNulls(Collections.emptyList()));

        final List<String> list = new ArrayList<>();
        list.add("test");
        assertTrue("Not null or empty collection should be passing", helper.isPopulatedAndHasNoNulls(list));

        list.add(null);
        assertFalse("Collection has a null object, it should be failing", helper.isPopulatedAndHasNoNulls(list));


    }

}