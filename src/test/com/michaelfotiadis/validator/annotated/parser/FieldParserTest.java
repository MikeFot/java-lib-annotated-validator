package com.michaelfotiadis.validator.annotated.parser;

import com.michaelfotiadis.validator.annotated.processor.SearchPolicy;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class FieldParserTest {

    private FieldParser mFieldParser;

    @Before
    public void setUp() throws Exception {
        mFieldParser = new FieldParser();
    }

    @Test
    public void isWrapperType() throws Exception {

        assertTrue(FieldParser.isWrapperType(String.class));
        assertTrue(FieldParser.isWrapperType(Integer.class));
        assertTrue(FieldParser.isWrapperType(Float.class));
        assertTrue(FieldParser.isWrapperType(BigInteger.class));
        assertTrue(FieldParser.isWrapperType(Byte.class));

        assertFalse(FieldParser.isWrapperType(Collection.class));
        assertFalse(FieldParser.isWrapperType(Map.class));
        assertFalse(FieldParser.isWrapperType(Iterable.class));
        assertFalse(FieldParser.isWrapperType(TestItem1.class));
        assertFalse(FieldParser.isWrapperType(TestNestedItem1.class));

    }

    @Test
    public void getAllFieldsShallow() throws Exception {

        final TestItem1 item1 = new TestItem1();

        final List<Field> fields = mFieldParser.getDeclaredFields(item1, SearchPolicy.SHALLOW);
        assertNotNull(fields);
        for (final Field field : fields) {
            assertNotNull(field);
            System.out.println(field.getDeclaringClass().getSimpleName() + " " + field.getName());
        }

        assertEquals(item1.getNumberOfShallowFields(), fields.size());
    }

    @Test
    public void getAllFieldsDeep() throws Exception {

        final TestItem1 item1 = new TestItem1();

        final List<Field> fields = mFieldParser.getDeclaredFields(item1, SearchPolicy.DEEP);
        assertNotNull(fields);
        for (final Field field : fields) {
            assertNotNull(field);
            System.out.println(field.getDeclaringClass().getSimpleName() + " " + field.getName());
        }

        assertEquals(item1.getNumberOfDeepFields(), fields.size());
    }

    @Test
    public void getArrayFieldsShallow() throws Exception {

        final TestArrayItem1 item1 = new TestArrayItem1();

        final List<Field> fields = mFieldParser.getDeclaredFields(item1, SearchPolicy.SHALLOW);
        assertNotNull(fields);
        for (final Field field : fields) {
            assertNotNull(field);
            System.out.println(field.getDeclaringClass().getSimpleName() + " " + field.getName());
        }

        assertEquals(item1.getNumberOfShallowFields(), fields.size());
    }

    @Test
    public void getArrayFieldsDeep() throws Exception {

        final TestArrayItem1 item1 = new TestArrayItem1();

        final List<Field> fields = mFieldParser.getDeclaredFields(item1, SearchPolicy.DEEP);
        assertNotNull(fields);
        for (final Field field : fields) {
            assertNotNull(field);
            System.out.println(field.getDeclaringClass().getSimpleName() + " " + field.getName());
        }

        assertEquals(item1.getNumberOfDeepFields(), fields.size());
    }


    @Test
    public void getArrayFieldsShallow2() throws Exception {

        final TestArrayItem2 item1 = new TestArrayItem2();

        final List<Field> fields = mFieldParser.getDeclaredFields(item1, SearchPolicy.SHALLOW);
        assertNotNull(fields);
        for (final Field field : fields) {
            assertNotNull(field);
            System.out.println(field.getDeclaringClass().getSimpleName() + " " + field.getName());
        }

        assertEquals(item1.getNumberOfShallowFields(), fields.size());
    }

    @Test
    public void getArrayFieldsDeep2() throws Exception {

        final TestArrayItem2 item1 = new TestArrayItem2();

        final List<Field> fields = mFieldParser.getDeclaredFields(item1, SearchPolicy.DEEP);
        assertNotNull(fields);
        for (final Field field : fields) {
            assertNotNull(field);
            System.out.println(field.getDeclaringClass().getSimpleName() + " " + field.getName());
        }

        assertEquals(item1.getNumberOfDeepFields(), fields.size());
    }

    @SuppressWarnings("unused")
    private class TestItem1 {
        private final String text = "A text";
        private final int number = 1;
        private final List<String> listString = Collections.singletonList("item");
        private final List<TestNestedItem1> listItem = Collections.singletonList(new TestNestedItem1());

        private int getNumberOfShallowFields() {
            return 2 + 3;
        }

        private int getNumberOfDeepFields() {
            return 2 + 5;
        }
    }

    @SuppressWarnings({"unused", "InnerClassMayBeStatic"})
    private class TestNestedItem1 {
        private final String text = "B text";
    }

    @SuppressWarnings("unused")
    private class TestArrayItem1 {

        private final TestNestedItem1[] array;

        private TestArrayItem1() {
            array = new TestNestedItem1[2];
            array[0] = new TestNestedItem1();
            array[1] = new TestNestedItem1();
        }

        private int getNumberOfShallowFields() {
            return 1 + 1;
        }

        private int getNumberOfDeepFields() {
            return 1 + 5;
        }
    }

    @SuppressWarnings("unused")
    private class TestArrayItem2 {

        private final TestArrayItem1[] array;

        private TestArrayItem2() {
            array = new TestArrayItem1[2];
            array[0] = new TestArrayItem1();
            array[1] = new TestArrayItem1();
        }

        private int getNumberOfShallowFields() {
            return 2;
        }

        private int getNumberOfDeepFields() {
            return 2 + 2 + 5 + 5;
        }
    }

}