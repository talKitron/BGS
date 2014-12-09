
package controller;


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
        //controller.deal(new Game(new Player("elad", "123")));      
        controller = Controller.getInstance();            
    }
}
