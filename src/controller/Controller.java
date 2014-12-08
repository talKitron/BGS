package controller;
import java.io.IOException;
import model.Deck;
import model.Game;
import model.Model;
import model.Player;
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
    public static Controller getInstance() throws IOException {       
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
     * The method adds player to system.
     * @param name
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
    
    public void deal(Game game) {
        model.deal(game);
    }
    
    
    
    

     /**
     * The method handle the exit from system.
     * @param logOut
     */
    public void executeSysExit(boolean logOut) throws IOException {
        model.executeSysExit(logOut);
    }
}