import org.junit.jupiter.api.Test;
import dtu.StringCalculator;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorJUnit {

  private final StringCalculator calc = new StringCalculator();

  @Test
  void testEmptyString() {
    assertEquals(0, calc.add(""));
  }

  @Test
  void testAddOneNumber() {
    assertEquals(10, calc.add("10"));
  }

  @Test
  void addWithDelimeter() {
    assertEquals(6, calc.add("2\n2,2"));
  }

  @Test
  void defineYourOwnDelimeter() {
    assertEquals(9, calc.add("//k\n2k2k2\n3"));
  }
}