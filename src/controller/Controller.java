package controller;

import java.io.IOException;
import model.Game;
import model.Model;
import model.Player;
import utilities.Constants;
import view.View;

/**
 * Controller Logic class.
 * @author BGS Team
 */
public final class Controller {

    /**
     * Singleton instance of this class, loaded on the first execution of ControllerLogic.getInstance()
     */
    private static Controller instance;
    /**
     * Boolean flag for class instance existence (singleton)
     */
    private static boolean exists = false;
    /**
     * ViewLogic reference pointer
     */
    private static View view; 
    /**
     * ModelLogic reference pointer
     */
    private static Model model; //assuming we've only one.
    //***************************************** Constructors ******************************************
    private Controller() {
       
    }
    //***************************************** Methods ***********************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    public static Controller getInstance(){       
            if (!exists) {
                exists = true;
                model = Model.getInstance();
                instance = new Controller();
                view = View.getInstance(instance);
                view.executeLoginView();
                return instance;
            }     
        return instance;
    }													
    //***************************************** CRUD Methods ******************************************
    
    /**
     * Checks if such name already exists in the database and if so it checks the password to complete login process.
     * else, it creates a new Player with the name.
     * @param name
     * @param password
     * @return 1-logged in; 2-password incorrect; 3-name illegal
     */
    public int loginProcess(String name, String password){
        return model.loginProcess(name, password);
    }
    
    /** 
     * The method adds player to system.
     * @param name
     * @param password
     * @return true if add successful
     */
    public Player addPlayer(String name, String password) {
        return model.addPlayer(name, password);
    }
    
    /** 
    * The method adds player to system.
    * @param player
    * @return true if add successful
    */
    public boolean addGame(Player player) {
        return model.addGame(player);
    }
    
    /**
     * Gets the current Player, creates a new Game for him (also dealing the initial cards) and adds it to the Database
     * @param player
     * @return the newly created Game, current Game
     */
    public Game deal(Player player) {
        return model.deal(player);
    }
    
     /**
     * The method handle the exit from system.
     * @param logOut
     */
    public void executeSysExit(boolean logOut){
        try {
            model.executeSysExit(logOut);
        } catch (IOException ex) {
            if (Constants.DEBUG){
                System.out.println(ex.getMessage());
            }
        }
    }
}
