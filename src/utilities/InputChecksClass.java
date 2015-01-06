package utilities;

import exceptions.*;

/**
 * Utility class for input checks.
 *
 * @author BGS Team
 */
public class InputChecksClass {

    /**
     * method for checking if the string is start with letter.
     *
     * @param string
     * @return true if start with space else return false
     */
    public static boolean startWithLetter(String string) {
        return !string.matches("^[A-Za-z].*$");
    }

    /**
     * method for checking text input. the method throw exceptions if the text contains numbers.
     *
     * @param text
     * @param label
     * @throws CheckIntException
     */
    public static void integerTest(String text, String label) throws CheckIntException {
        char[] chars = text.toCharArray();  // initialize chars array

        for (char c : chars) {
            if (Character.isDigit(c)) {  // check if char is digit
                throw new CheckIntException(label);
            }
        }
    }

    /**
     * method for checking text input. the method throw exceptions if the text contains letters.
     *
     * @param text
     * @param label
     * @throws CheckLettersException
     */
    public static void lettersTest(String text, String label) throws CheckLettersException {
        char[] chars = text.toCharArray(); // initialize chars array.
        for (char c : chars) {
            if (Character.isLetter(c)) { //// check if char is letter
                throw new CheckLettersException(label);
            }
        }
    }

}
