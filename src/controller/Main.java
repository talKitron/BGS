
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rawan
 */
public class Main  implements java.io.Serializable{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            try {
                 Controller.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
