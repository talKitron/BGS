package exceptions;
//***************************************** Imports *********************************************

import javax.swing.JOptionPane;

/**
 * Exception for letter occurrence checks.
 *
 * @author BGS Team
 */
public class CheckLettersException extends Exception {
//***************************************** Constructor *********************************************

    public CheckLettersException(String label) {
        //SoundClass sound = new SoundClass("/Sounds/error.wav"); // sound error
        JOptionPane.showMessageDialog(null, "The " + label + " field contains letters!", "Letters Error", JOptionPane.ERROR_MESSAGE);
    }
}
