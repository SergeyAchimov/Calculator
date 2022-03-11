import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Example {
  static Scanner s = new Scanner(System.in);

  public static void main(String[] args) {
    String num1, operation, num2, resultStr;
    int num1Int, num2Int, result;
    char operationChar;
    boolean romanFlag;
    // Regular expression
    String regex = "([0-9]{1,2}|[A-Za-z]{1,3})\s*([-|+|/|*])\s*([0-9]{1,2}|[A-Za-z]{1,3}$)";
    Pattern pattern = Pattern.compile(regex);
    while (true) {
      System.out.println("\nEnter mathematics expression: ");
      String name = s.nextLine();

      Matcher matcher = pattern.matcher(name);
      matcher.matches();
      try {
        num1 = matcher.group(1).toUpperCase();
        operation = matcher.group(2);
        num2 = matcher.group(3).toUpperCase();
      } catch (Exception e) {
        System.out.println("\nInvalid expression entered!!!\n");
        System.out.println(e.getMessage());
        return;
      }

      // Преобразование в int и char

      if (checkEnteredValue(num1) && checkEnteredValue(num2)) {
        num1Int = Integer.parseInt(num1);
        num2Int = Integer.parseInt(num2);
        romanFlag = false;
      } else if (!checkEnteredValue(num1) && !checkEnteredValue(num2)) {
        try {
          num1Int = romanToArabicInteger(num1);
          num2Int = romanToArabicInteger(num2);
          romanFlag = true;
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
          return;
        }
      } else {
        System.out.println("\nThe operands entered must be of the same type\n");
        return;

      }
      operationChar = operation.charAt(0);

      // Расчеты математические

      try {
        result = calculate(num1Int, num2Int, operationChar);
        resultStr = ((romanFlag) ? arabicToRomanString(result) : Integer.toString(result));
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
        return;
      }

      System.out.println("\n" + num1 + " " + operation + " " + num2 + " = "
          + resultStr + "\n");

    }
  }

  public static int calculate(int num1Int, int num2Int, char operationChar) throws Exception {
    int res;
    if (num1Int < 1 || num1Int > 10)
      throw new Exception("\n The numbers must be between 1 and 10 inclusive\n");
    if (num2Int < 1 || num2Int > 10)
      throw new Exception("\n The numbers must be between 1 and 10 inclusive\n");
    switch (operationChar) {
      case '+':
        res = num1Int + num2Int;
        break;
      case '-':
        res = num1Int - num2Int;
        break;
      case '/':
        res = num1Int / num2Int;
        break;
      case '*':
        res = num1Int * num2Int;
        break;
      default:
        throw new Exception("\n Invalid operator entered !!!\n");
    }
    return res;
  }

  public static int romanToArabicInteger(String num) throws Exception {
    int res;
    switch (num) {
      case "I":
        res = 1;
        break;
      case "II":
        res = 2;
        break;
      case "III":
        res = 3;
        break;
      case "IV":
        res = 4;
        break;
      case "V":
        res = 5;
        break;
      case "VI":
        res = 6;
        break;
      case "VII":
        res = 7;
        break;
      case "VIII":
        res = 8;
        break;
      case "IX":
        res = 9;
        break;
      case "X":
        res = 10;
        break;
      default:
        throw new Exception("\n\n Error. Invalid number entered !!! \n There will be no second attempt!!!\n\n");
      // res = 0;
    }
    return res;
  }

  public static boolean checkEnteredValue(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static String arabicToRomanString(int result) throws Exception {

    if (result < 1) {
      throw new Exception("The value can not be less then 1");
    }
    String msg1 = "";
    while (result > 100) {
      result -= 100;
      msg1 += "C";
    }
    while (result > 50) {
      result -= 50;
      msg1 += "L";
    }
    while (result > 10) {
      result -= 10;
      msg1 += "X";
    }
    while (result > 5) {
      result -= 5;
      msg1 += "V";
    }
    while (result > 0) {
      result -= 1;
      msg1 += "I";
    }
    return msg1;
  }

}
