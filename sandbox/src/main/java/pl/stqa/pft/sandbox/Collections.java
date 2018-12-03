package pl.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {

    String[] langs = {"JAVA", "C#", "Python", "PHP"};

    List<String> languages = Arrays.asList("JAVA", "C#", "Python", "PHP");

    for (String l : languages) {
      System.out.println("Chcę się nauczyć " + l);
    }
  }
}
