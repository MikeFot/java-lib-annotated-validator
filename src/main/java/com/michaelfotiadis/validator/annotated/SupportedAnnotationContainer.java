package com.michaelfotiadis.validator.annotated;

import com.michaelfotiadis.validator.annotated.utils.ReflectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public final class SupportedAnnotationContainer {

    private static final String PACKAGE_NAME = "com.michaelfotiadis.validator.annotated.annotations";
    private static final List<Class> SUPPORTED_CLASSES = new ArrayList<>();

    private SupportedAnnotationContainer() {
        // NOOP
    }

    /*package*/
    static boolean isSupported(final Class<?> type) {
        return getSupportedClasses().contains(type);
    }

    public static List<Class> getSupportedClasses() {
        if (SUPPORTED_CLASSES.isEmpty()) {
            final Class[] supportedArray;
            try {
                supportedArray = ReflectionUtils.getClasses(PACKAGE_NAME);
                SUPPORTED_CLASSES.addAll(Arrays.asList(supportedArray));
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        return SUPPORTED_CLASSES;
    }


}
