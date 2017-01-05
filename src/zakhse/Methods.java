package zakhse;

import java.util.Scanner;

/**
 * Some useful methods by Zakhse
 *
 * @author Zakhse
 * @version 0.1
 */
public class Methods {
    private static Scanner in = new Scanner(System.in);

    /**
     * Reads an integer number of int type from console.
     *
     * @param name What does entered number refer to.
     * @return Entered integer number of int type.
     * @since 0.1
     */
    public static int inputInt(String name) {
        while (true) {
            System.out.printf("Enter %s: ", name);
            String str = in.nextLine();
            if (tryParseInt(str)) {
                return Integer.parseInt(str);
            }
            System.out.println("Invalid value!\n");
        }
    }

    /**
     * Checks if a string can be parsed to int.
     *
     * @param value Checked string.
     * @return true if it's possible, false otherwise.
     * @since 0.1
     */
    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Reads a floating point number of double type from console.
     *
     * @param name What does entered number refer to.
     * @return Entered floating point  number of double type.
     * @since 0.1
     */
    public static double inputDouble(String name) {
        while (true) {
            System.out.printf("Enter %s: ", name);
            String str = in.nextLine();
            if (tryParseDouble(str)) {
                return Double.parseDouble(str);
            }
            System.out.println("Invalid value!\n");
        }
    }

    /**
     * Checks if a string can be parsed to double.
     *
     * @param value Checked string.
     * @return true if it's possible, false otherwise.
     * @since 0.1
     */
    public static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
