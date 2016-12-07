# Java Annotated Validator Library
Java Validator Library using custom runtime annotations and reflection

## Description

### Usage

#### Instantiation
- `new AnnotatedValidatorProcessor()`

- `new AnnotatedValidatorProcessor(SearchPolicy searchPolicy, FailPolicy failPolicy)`

**Option 1:** Instantiate a new #AnnotatedValidatorProcessor# via the `AnnotatedValidatorProcessor()` constructor. 
This will use a default CONTINUE / SHALLOW configuration.

**Option 2:** Instantiate a new `AnnotatedValidatorProcessor` via the `AnnotatedValidatorProcessor(SearchPolicy searchPolicy, FailPolicy failPolicy)` constructor.
This will use the provided parameters 

#### Validation
- `validate(T item)`

- `validate(T item, SearchPolicy searchPolicy, FailPolicy failPolicy)`

**Option 1:** Use the `validate(T item)` method. 
This will use the `FailPolicy` and `SearchPolicy` defined in the constructor.

**Option 2:** Use the `validate(T item, SearchPolicy searchPolicy, FailPolicy failPolicy)` method.
This will override the default `FailPolicy` and `SearchPolicy` with the provided ones.

##### Parameters
There are 2 parameters that you can use to configure the validator:
- `FailPolicy` :

    **FAIL_ON_FIRST_ERROR** - only the first issue of each field will be attached to the validation results
    
    **CONTINUE** - all issues will be recorder and attached to a list
    
- `SearchPolicy`: 

    **SHALLOW** - will just look at the first layer of the object, ignoring nested ones and contents of collections.
    
    **DEEP** - will recursively look deep into the object, ignoring objects it has already traversed.

##### Results
The validation will produce a `ValidationResultsContainer` for the entire operation. This object contains information if any fields failed to pass the validation rules and why.

The results can be accessed using the following container methods: 

- `isValid()` will give you *true* if ALL fields passed the validation rules, otherwise *false*.

- `getResults()` will give you a `Map<String, List<ValidationStatus>>` containing the name of each field as the key, and a list of validation statuses attached to it describing the results of the validation. If the field has failed more than one checks, multiple codes will be in the list if `FailPolicy` is set to **CONTINUE**.

- `getFailedFieldNames()` will give you a `Set<String>` of field names that failed the validation. This will help you quickly find where the issue is located.

- `getFailedStatuses()` will give you a `Set<ValidationStatus>` for each `ValidationStatus` that is not equal to **SUCCESS**. This will help you identify the range of failures.

- `ValidationStatus` is an enum attached to results describing the issue. Allowed values are:
    
    ```
    UNDEFINED_FAILURE,    
    NULL_VALUE,    
    EMPTY_COLLECTION,    
    COLLECTION_CONTAINS_NULL,    
    PATTERN_DID_NOT_MATCH,    
    EMPTY_STRING,    
    INVALID_VALUE,    
    WRONG_TYPE_ARGUMENTS,    
    VALUE_OUT_OF_RANGE,    
    NUMBER_OUT_OF_RANGE,
    INTEGER_OUT_OF_RANGE,
    BYTE_OUT_OF_RANGE,
    DOUBLE_OUT_OF_RANGE,
    FLOAT_OUT_OF_RANGE,
    EXCEPTION,
    SUCCESS
    ```

##### Annotations
- Array
    ```
    @ArrayContainsNoNulls
    @ArrayContainsValue(Class<?> value)
    @ArrayIsNotEmpty
    ```
- Collection
    ```
    @CollectionContainsClass(Class<?> value)
    @CollectionContainsNoNulls
    @CollectionIsNotEmpty
    ```
- Conditional
    ```
    @BooleanEqualsValue(boolean value)
    ```
- General
    ```
    @IsNull
    
    @NotNull
    ```
- Numeric
    ```
    @ByteEqualsValue(byte value)

    @DoubleEqualsValue(double value, double epsilon)
    @DoubleMaxValue(double max, double epsilon)
    @DoubleMinValue(double min, double epsilon)

    @FloatEqualsValue(float value, double epsilon)
    @FloatMaxValue(float max, double epsilon)
    @FloatMinValue(float min, double epsilon)

    @IntegerEqualsValue(int value)
    @IntegerMaxValue(int value)
    @IntegerMinValue(int value)

    @ShortEqualsValue(int value)
    @ShortMaxValue(int value)
    @ShortMinValue(int value)
    ```
- Text
    ```
    @TextDateFormat(String value)
    @TextEmail
    @TextExactLength(int value)
    @TextIsNumeric
    @TextMatchesExpression(String expression)
    @TextMaxLength(int value)
    @TextMinLength(int value)
    @TextNotNullOrEmpty
    @TextUrl
    ```

##### Sample Objects

```
class SampleObject1 {

        private static final double EPSILON = 0.0000000001d;
        
        @IntegerMaxValue(20)
        @IntegerMinValue(0)
        Integer testIntegerMinMax;
        @IntegerEqualsValue(5)
        int testIntegerEquals;
        @NotNull
        @DoubleMaxValue(max = -4345454.4339493991d, epsilon = EPSILON)
        Double testDoubleMax;
        @DoubleMinValue(min = 1232.1d, epsilon = EPSILON)
        double testDoubleMin;
        @FloatEqualsValue(value = 5f, epsilon = EPSILON)
        float testFloatEquals;
        @FloatMaxValue(max = 24.4f, epsilon = EPSILON)
        Float testFloatMax;
        @IsNull
        Integer testIsNullInteger;

}
```

```
class SampleObject2 {
        @NotNull
        @TextDateFormat("yyyy-MM-dd")
        String textDate;
        @TextEmail()
        String textEmail;
        @TextMinLength(7)
        String text;
}
    
```
## Importing into your project

**Gradle**

Add the following to your parent project Gradle file

```
allprojects {
        repositories {
           maven { url "https://dl.bintray.com/mikefot/maven/" }
        }
    }
```

And the following to your module Gradle file:

`compile 'mikefot:com.michaelfotiadis.validator.annotated:1.0.1'`

**Maven**

```
<dependency>
  <groupId>mikefot</groupId>
  <artifactId>com.michaelfotiadis.validator.annotated</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

**Bintray Project**
https://bintray.com/mikefot/maven/java-lib-annotated-validator

## Versions
- 1.0.1 : Fixed an issue with pre-19 Android devices caused by ReflectiveOperationException

## Target
This library is targetting Java 7 for backwards compatibility with Android.
