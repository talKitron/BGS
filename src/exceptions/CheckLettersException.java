/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

//***************************************** Imports *********************************************
import javax.swing.JOptionPane;
import view.SoundClass;

public class CheckLettersException extends Exception {
//***************************************** Constructor *********************************************

    public CheckLettersException(String label) {
        SoundClass sound = new SoundClass("/Sounds/error.wav"); // sound error
        JOptionPane.showMessageDialog(null, "The " + label + " field contains letters!", "Letters Error", JOptionPane.ERROR_MESSAGE);
    }
}
