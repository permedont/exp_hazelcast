package ru.edu.tests.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * Выбирается самый узкоспециализированный класс, если 2 класса на одном уровне специализации, то не компилирется
 */
public class Overload {


    private String method(Object o) {
        return "Object";

    }

    private String method(java.io.FileNotFoundException f) {
        return "FileNotFoundException";
    }

    private String method(java.io.IOException i) {
        return "IOException";

    }

    @Test
    public void test() {
        Overload test = new Overload();
        String methodResult = test.method(null);
        Assert.assertEquals("FileNotFoundException", methodResult);
    }
}