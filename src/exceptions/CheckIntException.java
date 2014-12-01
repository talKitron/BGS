/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

//***************************************** Imports *********************************************
import javax.swing.JOptionPane;
import view.SoundClass;

public class CheckIntException extends Exception {
//***************************************** Constructor *********************************************

    public CheckIntException(String label) {
        SoundClass sound = new SoundClass("/Sounds/error.wav"); // sound error
        JOptionPane.showMessageDialog(null, "The " + label + " contains integers!", "Integers Error", JOptionPane.ERROR_MESSAGE);
    }
}
