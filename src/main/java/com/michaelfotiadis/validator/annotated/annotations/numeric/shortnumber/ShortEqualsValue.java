package com.michaelfotiadis.validator.annotated.annotations.numeric.shortnumber;

import com.michaelfotiadis.validator.annotated.annotations.AnnotationCategory;
import com.michaelfotiadis.validator.annotated.annotations.Category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Category(type = AnnotationCategory.INTEGER)
public @interface ShortEqualsValue {
    short value();
}