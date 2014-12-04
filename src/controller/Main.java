
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        
        
            try {
                 controller = Controller.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            controller.deal(new Game(new Player("elad", "123")));
            
    }
    
}
