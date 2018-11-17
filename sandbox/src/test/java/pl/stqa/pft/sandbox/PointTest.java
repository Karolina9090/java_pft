package pl.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {


  @Test
  public void testDistance() {
    Point p1 = new Point(0, 5);
    Point p2 = new Point(0, 10);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }
  @Test
  public void testDistance1() {
    Point p1 = new Point(0, 40);
    Point p2 = new Point(0, 80);
    Assert.assertEquals(p1.distance(p2), 40.0);
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(0, 2);
    Point p2 = new Point(0, 4);
    Assert.assertEquals(p1.distance(p2), 2.0);
  }
}
