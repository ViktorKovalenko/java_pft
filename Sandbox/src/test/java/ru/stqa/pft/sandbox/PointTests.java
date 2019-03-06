package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance() {
        Point p1 = new Point(3, 2);
        Point p2 = new Point(3, 5);
        Assert.assertEquals(Point.distance(p1, p2), 3);
    }
}