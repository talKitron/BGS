
package view;

//*************************************************** Imports *****************************************************//

import controller.Controller;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Card;
import model.Game;
import model.Player;
import utilities.Constants;

/**
 * View class to communicate with Controller class and also make the UI/UX happen.
 * @author BGS Team
 */
public final class View {

//*************************************************** Variables *****************************************************// 
    /**
     * Singleton instance of this class, loaded on the first execution of ViewLogic.getInstance()
     */
    private static View instance;
    /**
     * the loginFrame
     */
    private static MainFrame mainFrame;
    /**
     * Boolean flag for class instance existence (singleton)
     */
    private static boolean exist = false;
    /**
     * ControllerLogic reference pointer
     */
    private static Controller controller;
    /**
     * Holds current player details
     */
    public static Player currentPlayer;
//*************************************************** Constructors *************************************************
    /**
     * empty constructor.
     */
    public View() {
    }
//*************************************************** Methods *************************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @param ins
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    public static View getInstance(Controller ins) {
        if (!exist) {
            controller = ins;
        }
        if (controller != null) {     // if controller is null create new viewLogic object
            exist = true;
            instance = new View();
            return instance;
        }
        return null;
    }
    /**
     * create new MainFrame
     */
    public void executeLoginView() {
        mainFrame = new MainFrame(instance);      // create new login frame
    }
     
     /**
     * the method gets address of sound file and return the sound for play.
     * @param soundAddress
     * @return 
     */
    public SoundClass sound(String soundAddress) {          ///////// sound method
        SoundClass sound = new SoundClass(soundAddress);
        return sound;
    }
    /**
     * the methods clear all fields that she get
     * using ellipsis
     * @param text 
     */
    public void clearFields(JTextField... text) {            // ellipsis method to clear fields
        for (JTextField t : text) {
            t.setText("");
        }
    }
    
    /**
     * Checks if such name already exists in the database and if so it checks the password to complete login process.
     * else, it creates a new Player with the name.
     * @param name
     * @param password
     * @return 1-logged in; 2-password incorrect;
     */
    protected int loginProcess(String name, String password){
        return controller.loginProcess(name, password);
    }
    
    /**
     * Gets the current Player, creates a new Game for him (also dealing the initial cards) and adds it to the Database.
     * @param player
     * @return the newly created Game, current Game
     */
    protected Game deal(Player player) {
        return controller.deal(player);
    }
    
    /**
     * receives a Card and returns path for its image.
     * @param card
     * @return path of image for given card
     */
    protected String getCardImage(Card card){
        String cardImageName = card.toString().replace(" ", "_");
        String imagePath = "/resources/cards/" + cardImageName + ".png";
        if (Constants.DEBUG){
            System.out.println(imagePath);
        }
        return imagePath;
    }
    /**
     * receives the TableFrame frame and adds a card to it.
     * @param tableFrame 
     */
    void drawCard(JLabel lbl, Card card) {
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource(getCardImage(card))));
    }
    
    /**
     * @return current Player
     */
    Player getCurrentPlayer(){
        currentPlayer = controller.getCurrentPlayer();
        return currentPlayer;
    }
    
    /**
     * @return a random fact about Blackjack
     */
    public String getFact(){
        return controller.getFact();
    }
    
    /**
     * method executes the system's exit.
     * @param logOut
     * @throws IOException 
     */
    public void executeSysExit(boolean logOut) throws IOException {
        controller.executeSysExit(logOut);
    }
}
