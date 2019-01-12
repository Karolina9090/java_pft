package pl.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SqureTest {

  @Test
  public void testArea() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 20.0);
  }
}
