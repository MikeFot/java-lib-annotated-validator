# java-lib-annotated-validator
Java Validator Library using annotations


## Description


### Usage

#### Instantiation
`new AnnotatedValidatorProcessor()`

`new AnnotatedValidatorProcessor(SearchPolicy searchPolicy, FailPolicy failPolicy)`

**Option 1:** Instantiate a new #AnnotatedValidatorProcessor# via the `AnnotatedValidatorProcessor()` constructor. 
This will use a default CONTINUE / SHALLOW configuration.

**Option 2:** Instantiate a new `AnnotatedValidatorProcessor` via the `AnnotatedValidatorProcessor(SearchPolicy searchPolicy, FailPolicy failPolicy)` constructor.
This will use the provided parameters 

#### Validation
`validate(T item)`

`validate(T item, SearchPolicy searchPolicy, FailPolicy failPolicy)`

**Option 1:** Use the `validate(T item)` method. 
This will use the `FailPolicy` and `SearchPolicy` defined in the constructor.

**Option 2:** Use the `validate(T item, SearchPolicy searchPolicy, FailPolicy failPolicy)` method.
This will override the default `FailPolicy` and `SearchPolicy` with the provided ones.

##### Parameters
There are 2 parameters that you can use to configure the validator:
- FailPolicy :
    FAIL_ON_FIRST_ERROR - only the first issue of each field will be attached to the validation results
    CONTINUE - all issues will be recorder and attached to a list
- SearchPolicy: 
    SHALLOW - will just look at the first layer of the object, ignoring nested ones and contents of collections.
    DEEP - will recursively look deep into the object, ignoring objects it has already traversed.

##### Results
The validation will produce a `ValidationResultsContainer` for the entire operation. This object contains information if any fields failed to pass the validation rules and why.

The results can be accessed using the following container methods: 

`isValid()` will give you *true* if ALL fields passed the validation rules, otherwise *false*.

`getResults()` will give you a `Map<String, List<ValidationStatus>>` containing the name of each field as the key, and a list of validation statuses attached to it describing the results of the validation. If the field has failed more than one checks, multiple codes will be in the list if **FailPolicy** is set to **CONTINUE**.

`getFailedFieldNames()` will give you a `Set<String>` of field names that failed the validation. This will help you quickly find where the issue is located.

`getFailedStatuses()` will give you a `Set<ValidationStatus>` for each `ValidationStatus` that is not equal to **SUCCESS**. This will help you identify the range of failures.

`ValidationStatus` is an enum attached to results describing the issue. Allowed values are:
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

## Target
This library is targetting Java 7 for backwards compatibility with Android.
