
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.*;
import exceptions.*;
import model.Game;
import model.Player;


/**
 * Main class to run the BGS system.
 * @author BGS Team
 */
public class Main  implements java.io.Serializable{
    /**
     * @param args the command line arguments
     */
    private static Controller controller;
    public static void main(String[] args) {
            
        controller = Controller.getInstance();

        
        
        /**
        * Tests Block
        */
        
        /*
        GenericTests test = new GenericTests();
        int errorCode1 = test.SimpleCardsTest();    //run SimpleCardsTest
        if (errorCode1 != 0) try {
            throw new exceptions.CardDeckTestException("Error code "+errorCode1);
        } catch (CardDeckTestException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        int errorCode2 = test.SimpleUIVisabilityTest();     //run SimpleUIVisabilityTest
        if (errorCode2 != 0) try {
            throw new exceptions.UITestException("Error code "+errorCode2);
        } catch (UITestException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    
    
}
