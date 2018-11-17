package pl.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Karolina");

    Square s = new Square(5);
    System.out.println("Powierzchnia kwadratu o boku " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Powierzchnia prostokątu o bokach " + r.a + " i " + r.b + " = " + r.area());

    Point p1 = new Point(4, 0);
    Point p2 = new Point(-4, 0);
    System.out.println("Odległość między punktami: " + p1.distance(p2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }
}
