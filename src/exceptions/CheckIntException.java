package exceptions;
//***************************************** Imports *********************************************

import javax.swing.JOptionPane;

/**
 * Exception for Integer checking
 *
 * @author BGS Team
 */
public class CheckIntException extends Exception {
//***************************************** Constructor *********************************************

    public CheckIntException(String label) {
        //SoundClass sound = new SoundClass("/Sounds/error.wav"); // sound error
        JOptionPane.showMessageDialog(null, "The " + label + " contains integers!", "Integers Error", JOptionPane.ERROR_MESSAGE);
    }
}
