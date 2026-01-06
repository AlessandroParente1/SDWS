package dtu;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

  public int add(String s) {

    if (s.isEmpty()) {
      return 0;
    }

    String delimiter = ",";

    if (s.charAt(0) == '/' && s.charAt(1) == '/') {
      int i = 2;

      while (i < s.length() && s.charAt(i) != '\n') { // note \n is treatred as one char
        i++;
      }

      delimiter = s.substring(2, i);
      s = s.substring(i + 1);
    }

    String[] array = s.split("[" + delimiter + "\n]");
    int sum = 0;
    List<Integer> negatives = new ArrayList<>();

    for (String item : array) {
      int num = Integer.parseInt(item);
      if (num < 0) {
        negatives.add(num);
      }
      sum += num;
    }

    if (!negatives.isEmpty()) {
      throw new IllegalArgumentException("negatives not allowed: " + negatives);
    }

    return sum;
  }

  public static void main(String[] args) {
    StringCalculator sc = new StringCalculator();

    System.out.println("Test 1: " + sc.add("")); // 0
    System.out.println("Test 2: " + sc.add("2\n2,3")); // 7 (2+2+3)
    System.out.println("Test 3: " + sc.add("//;\n1;2\n3")); // 6 (1+2+3)
  }
}