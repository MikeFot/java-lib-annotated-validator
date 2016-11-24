# java-lib-annotated-validator
Java Validator Library using annotations


## Description


### Usage

#### Instantiation
`new AnnotatedValidatorProcessor()`

`new AnnotatedValidatorProcessor(SearchPolicy searchPolicy, FailPolicy failPolicy)`

Option 1: Instantiate a new #AnnotatedValidatorProcessor# via the `AnnotatedValidatorProcessor()` constructor. 
This will use a default CONTINUE / SHALLOW configutarion.

Option 2: Instantiate a new `AnnotatedValidatorProcessor` via the `AnnotatedValidatorProcessor(SearchPolicy searchPolicy, FailPolicy failPolicy)` constructor.
This will use the provided parameters 

#### Usage
`validate(T item)`

`validate(T item, SearchPolicy searchPolicy, FailPolicy failPolicy)`

Option 1: Use the `validate(T item)` method. 
This will use the `FailPolicy` and `SearchPolicy` defined in the constructor.

Option 2: Use the `validate(T item, SearchPolicy searchPolicy, FailPolicy failPolicy)` method.
This will override the default `FailPolicy` and `SearchPolicy` with the provided ones.

##### Parameters
There are 2 parameters that you can use to configure the validator:
- FailPolicy :
    FAIL_ON_FIRST_ERROR - only the first issue of each field will be attached to the validation results
    CONTINUE - all issues will be recorder and attached to a list
- SearchPolicy: 
    SHALLOW - will just look at the first layer of the object, ignoring nested ones and contents of collections.
    DEEP - will recursively look deep into the object, ignoring objects it has already traversed.

## Target
This library is targetting Java 7 for backwards compatibility with Android.
