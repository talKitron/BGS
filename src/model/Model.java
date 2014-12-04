package model;
import java.io.IOException;


/**
 * Model Logic class
 * @author BGS Team
 */
public final class Model implements I_Model, java.io.Serializable {
    //***************************************** Variables *********************************************
    /**
     * Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()
     */
    private static Model instance;
    /**
     * Boolean flag for class instance existence (singleton)
     */
    private static boolean exists = false;
    /**
     * SysData reference pointer
     */
    private static DataBase db;
    //***************************************** Constructors ******************************************
    private Model() {
       
    }
    //***************************************** Methods ***********************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     * @throws GeneralException
     */
    public static Model getInstance() throws IOException {
        if (!exists) {
           
            db = DataBase.getInstance();
            if (db != null) {
                db.executeInput();
                exists = true;
                instance = new Model();
                return instance;
            }
        }
        return null;
    }
    /**
     * The method handle the exit from system.
     * @param logOut
     * @throws java.io.IOException
     */
    public void executeSysExit(boolean logOut) throws IOException {
        db.executeOutput(logOut);
    }
    
    /** 
     * The method adds player to system.
     * @param name
     * @param password
     * @return true if add successful
     */
    @Override
    public boolean addPlayer(String name, String password) {

        if(name!=null && password!=null)
        {
            if(!db.getPlayers().containsKey(name))
            {
                db.getPlayers().put(name, new Player(name, password));
                return true;
            }
        }
        return false;
    }
    /** 
    * The method adds player to system.
    * @param player
    * @return true if add successful
    */
    @Override
    public boolean addGame(Player player) {

        if(player!=null)
        {
            Game game = new Game (player);
            if(!db.getGames().contains(game))
            {
                db.getGames().add(game);
                return true;
            }
        }
        return false;
    }
    
    public void deal(Game game) {
        game.deal();
    }
}
