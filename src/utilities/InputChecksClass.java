package utilities;

import exceptions.*;


public class InputChecksClass {
    /**
     * method for checking text input. the method throw exceptions if the text contains numbers.
     * @param text
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
     * @param text
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