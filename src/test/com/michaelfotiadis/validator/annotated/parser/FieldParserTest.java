package com.michaelfotiadis.validator.annotated.parser;

import com.michaelfotiadis.validator.annotated.annotations.array.ArrayIsNotEmpty;
import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionContainsClass;
import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionContainsNoNulls;
import com.michaelfotiadis.validator.annotated.annotations.collection.CollectionIsNotEmpty;
import com.michaelfotiadis.validator.annotated.annotations.text.TextEmail;
import com.michaelfotiadis.validator.annotated.annotations.text.TextIsNumeric;
import com.michaelfotiadis.validator.annotated.annotations.text.TextMaxLength;
import com.michaelfotiadis.validator.annotated.annotations.text.TextNotNullOrEmpty;
import com.michaelfotiadis.validator.annotated.processor.SearchPolicy;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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

        final List<Field> fields = FieldParser.getAnnotatedFields(item1, SearchPolicy.SHALLOW);
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

        final List<Field> fields = FieldParser.getAnnotatedFields(item1, SearchPolicy.DEEP);
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

        final List<Field> fields = FieldParser.getAnnotatedFields(item1, SearchPolicy.SHALLOW);
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

        final List<Field> fields = FieldParser.getAnnotatedFields(item1, SearchPolicy.DEEP);
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

        final List<Field> fields = FieldParser.getAnnotatedFields(item1, SearchPolicy.SHALLOW);
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

        final List<Field> fields = FieldParser.getAnnotatedFields(item1, SearchPolicy.DEEP);
        assertNotNull(fields);
        for (final Field field : fields) {
            assertNotNull(field);
            System.out.println(field.getDeclaringClass().getSimpleName() + " " + field.getName());
        }

        assertEquals(item1.getNumberOfDeepFields(), fields.size());
    }

    @Test
    public void getArrayFieldsDeep3() throws Exception {

        final TestTextItemValidInvalidDeep item1 = new TestTextItemValidInvalidDeep();

        final List<Field> fields = FieldParser.getAnnotatedFields(item1, SearchPolicy.DEEP);
        assertNotNull(fields);
        for (final Field field : fields) {
            assertNotNull(field);
            System.out.println(field.getDeclaringClass().getSimpleName() + " " + field.getName());
        }

        assertEquals(2, fields.size());
    }

    @Test
    public void getValuesDeep() throws Exception {

        final TestTextItemValidInvalidDeep item1 = new TestTextItemValidInvalidDeep();

        final Collection<Field> collection = FieldParser.getAnnotatedFields(item1, SearchPolicy.DEEP);
        for (final Field o : collection) {
            System.out.println(o.getName());
            for (final Annotation annotation : o.getAnnotations()) {
                System.out.println(annotation.toString());
            }
        }
        assertNotNull(collection);
        assertEquals(2, collection.size());

    }

    @Test
    public void getValuesDeep2Layers() throws Exception {

        final TestTextItemValidInvalidInvalidDeep item1 = new TestTextItemValidInvalidInvalidDeep();

        final Collection<Field> collection = FieldParser.getAnnotatedFields(item1, SearchPolicy.DEEP);
        for (final Field o : collection) {
            System.out.println(o.getName());
            for (final Annotation annotation : o.getAnnotations()) {
                System.out.println(annotation.toString());
            }
        }
        assertNotNull(collection);
        assertEquals(3, collection.size());

    }

    @Test
    public void getValuesShallow() throws Exception {

        final TestTextItemValidInvalidDeep item1 = new TestTextItemValidInvalidDeep();

        final Collection<Field> collection = FieldParser.getAnnotatedFields(item1, SearchPolicy.SHALLOW);
        for (final Field o : collection) {
            System.out.println(o.getName());
            for (final Annotation annotation : o.getAnnotations()) {
                System.out.println(annotation.toString());
            }
        }
        assertNotNull(collection);
        assertEquals(1, collection.size());

    }


    @Test
    public void getValuesShallow2Layers() throws Exception {

        final TestTextItemValidInvalidInvalidDeep item1 = new TestTextItemValidInvalidInvalidDeep();

        final Collection<Field> collection = FieldParser.getAnnotatedFields(item1, SearchPolicy.SHALLOW);
        for (final Field o : collection) {
            System.out.println(o.getName());
            for (final Annotation annotation : o.getAnnotations()) {
                System.out.println(annotation.toString());
            }
        }
        assertNotNull(collection);
        assertEquals(1, collection.size());

    }


    @Test
    public void getValuesShallowLists2Layers() throws Exception {

        final TestNestedLists item = new TestNestedLists();

        final Collection<Field> collection = FieldParser.getAnnotatedFields(item, SearchPolicy.SHALLOW);
        for (final Field o : collection) {
            System.out.println(o.getName());
            for (final Annotation annotation : o.getAnnotations()) {
                System.out.println(annotation.toString());
            }
        }
        assertNotNull(collection);
        assertEquals(2, collection.size());

    }

    @Test
    public void getValuesDeepLists2Layers() throws Exception {

        final TestNestedLists item = new TestNestedLists();

        final Collection<Field> collection = FieldParser.getAnnotatedFields(item, SearchPolicy.DEEP);
        for (final Field o : collection) {
            System.out.println(o.getName());
            for (final Annotation annotation : o.getAnnotations()) {
                System.out.println(annotation.toString());
            }
        }
        assertNotNull(collection);
        assertEquals(3, collection.size());

    }

    @SuppressWarnings("unused")
    private class TestItem1 {
        @TextMaxLength(10)
        private static final String text = "A text";
        @TextIsNumeric
        private static final int number = 1;
        @CollectionContainsClass(String.class)
        private final List<String> listString = Collections.singletonList("item");
        @CollectionContainsNoNulls
        private final List<TestNestedItem1> listItem = Collections.singletonList(new TestNestedItem1());

        private int getNumberOfShallowFields() {
            return 4;
        }

        private int getNumberOfDeepFields() {
            return 5;
        }
    }

    @SuppressWarnings("unused")
    private class TestItem2 extends TestItem1 {
        private static final int number = 2;

        private int getNumberOfShallowFields() {
            return 2 + 3;
        }

        private int getNumberOfDeepFields() {
            return 2 + 5;
        }
    }

    @SuppressWarnings({"unused", "InnerClassMayBeStatic"})
    private class TestNestedItem1 {
        @TextNotNullOrEmpty
        private static final String text = "B text";
    }

    @SuppressWarnings("unused")
    private final class TestArrayItem1 {
        @ArrayIsNotEmpty
        private final TestNestedItem1[] array;

        private TestArrayItem1() {
            array = new TestNestedItem1[2];
            array[0] = new TestNestedItem1();
            array[1] = new TestNestedItem1();
        }

        private int getNumberOfShallowFields() {
            return 1;
        }

        private int getNumberOfDeepFields() {
            return 3;
        }
    }

    @SuppressWarnings("unused")
    private final class TestArrayItem2 {

        private final TestArrayItem1[] array;

        private TestArrayItem2() {
            array = new TestArrayItem1[2];
            array[0] = new TestArrayItem1();
            array[1] = new TestArrayItem1();
        }

        private int getNumberOfShallowFields() {
            return 0;
        }

        private int getNumberOfDeepFields() {
            return 6;
        }
    }

    @SuppressWarnings("unused")
    private final class TestMapItem1 {

        private final Map<Integer, TestItem1> map;

        private TestMapItem1() {
            map = new HashMap<>();
            map.put(0, new TestItem1());
            map.put(1, new TestItem2());
        }
    }

    private static final class TestTextItemInvalid {
        @TextEmail
        private static final String textEmailInvalid = "email@domain";
    }

    private static final class TestTextItemValidInvalidDeep {
        @TextEmail
        private static final String textEmailValid = "email@domain.co.uk";
        private static final TestTextItemInvalid innerInvalidItem = new TestTextItemInvalid();
    }

    private static final class TestTextItemValidInvalidInvalidDeep {
        @TextEmail
        private static final String textEmailValid = "email@domain.co.uk";
        private static final TestTextItemValidInvalidDeep innerValidInvalidItem = new TestTextItemValidInvalidDeep();
    }

    private static final class TestNestedLists {

        @CollectionIsNotEmpty
        private static final List<Integer> list1 = Arrays.asList(1, 2, 3, 4);

        @CollectionIsNotEmpty
        private static final List<NestedListContainer> list2 = Collections.singletonList(new NestedListContainer());

        private static final class NestedListContainer {

            @CollectionIsNotEmpty
            private static final List<String> list = Arrays.asList("Bob", "Mike");

        }

    }



}