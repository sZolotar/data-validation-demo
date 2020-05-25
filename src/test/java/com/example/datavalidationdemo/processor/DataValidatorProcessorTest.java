package com.example.datavalidationdemo.processor;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataValidatorProcessorTest {

    private DataValidatorProcessor validatorProcessor = new DataValidatorProcessor();

    @Test
    public void testValueValidation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = DataValidatorProcessor.class.getDeclaredMethod("getValidationValue", double.class);
        method.setAccessible(true);

        assertEquals(1, method.invoke(validatorProcessor, 0.1));
        assertEquals(1, method.invoke(validatorProcessor,99.9));
        assertEquals(1, method.invoke(validatorProcessor,48));
        assertEquals(1, method.invoke(validatorProcessor,52.0));
        assertEquals(1, method.invoke(validatorProcessor,0));
        assertEquals(1, method.invoke(validatorProcessor,100));

        assertEquals(0, method.invoke(validatorProcessor,-0.1));
        assertEquals(0, method.invoke(validatorProcessor,-1));
        assertEquals(0, method.invoke(validatorProcessor,120));
        assertEquals(0, method.invoke(validatorProcessor,100.1));
    }

}