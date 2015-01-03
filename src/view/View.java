
package view;

//*************************************************** Imports *****************************************************//

import controller.Controller;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Card;
import model.Game;
import model.Player;
import utilities.Constants;

/**
 * View class to communicate with Controller class and also make the UI/UX happen.
 * @author BGS Team
 */
public final class View{

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
     * The method creates this class\'s instance & provides access to it, by returning a reference (singleton).
     * @param ins
     * @return reference to this class\'s only instance, or null if reference was already returned (singleton).
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
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            if (Constants.DEBUG){
                System.out.println(e.getMessage());
            }
        }
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
    
    protected void setFrameIcon(JFrame frm){
        try {
            Image i = ImageIO.read(getClass().getResource(Constants.BGS_ICON_PATH));
            frm.setIconImage(i);
        } catch (IOException ex) {
            if (Constants.DEBUG){
                System.out.println(ex.getMessage());
            }
        }
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
     * The method adds player to system.
     * @param name
     * @param password
     * @return true if add successful
     */
    public Player addPlayer(String name, String password) {
        return controller.addPlayer(name, password);
    }
    
    /** 
    * The method creates a new Game in the system.
    * @param player
    * @return newly created game
    */
    public Game addGame(Player player) {
        return controller.addGame(player);
    }
    
   /**
     * Occurs after clicking on the "Deal" button, deals two cards for player and dealer.
     * @param game
     */
    protected void deal(Game game) {
        controller.deal(game);
    }
    
    /**
     * receives a Card and returns path for its image.
     * @param card
     * @return path of image for given card
     */
    protected String getCardImage(Card card){
        String cardImageName = card.toString().replace(" ", "_").toLowerCase();
        String imagePath = "/resources/cards/" + cardImageName + ".png";
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
     * @param game
     * @return true if player hand is more than 21 and false if not
     */
    public boolean isBusted(Game game) {
        return controller.isBusted(game);
    }
    
    /**
     * Occurs after clicking on the "Stand" button, check dealer cards
     * @param game
     * @return The last Card dealt to Dealer
     */
    public Card stand(Game game) {
        return controller.stand(game);
    }
    
     /**
     * @param game
     * @return true if player win and false if not
     */
    public boolean whoWon(Game game) {
        return controller.whoWon(game);
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
     * @return BGS rules.
     */
    public String[] getRules(){
        return controller.getRules();
    }
    
    /**
     * The method bills the Player for fun.
     * @param game
     * @param drinkName
     */
    public void orderDrink(Game game, String drinkName){
        controller.orderDrink(game, drinkName);
    }
    
    /**
     * @return game with high score
     */
    public Game getHighScoreGame () {      
        return controller.getHighScoreGame();
    }
    /**
     * @return game with high win
     */
    public Game getHighWinsGame () {      
        return controller.getHighWinsGame();
    }
    /**
     * @return game with high lose
     */
    public Game getHighLosesGame () {      
        return controller.getHighLosesGame();
    }
    
     /**
     * The method handle the exit from system.
     * @param logOut
     */
    public void executeSysExit(boolean logOut){
        try {
            controller.executeSysExit(logOut);
        } catch (IOException ex) {
            if (Constants.DEBUG){
                System.out.println(ex.getMessage());
            }
        }
    }
}
