package ru.ltz.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter () {
        var triangle = new Triangle(3.0, 4.0, 5.0);
        var result = triangle.calculatePerimeter();
        Assertions.assertEquals(12.0, result);
    }

    @Test
    void canCalculateArea () {
        var triangle = new Triangle(3.0, 4.0, 5.0);
        var result = triangle.calculateArea();
        Assertions.assertEquals(6.0, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-3.0, 4.0, 5.0);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //
        }
    }

    @Test
    void cannotCreateTriangleWithoutTriangleInequality () {
        try {
            new Triangle(1.0, 2.0, 5.0);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //
        }
    }
}
