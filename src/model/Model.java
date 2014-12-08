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
     * Checks if such name already exists in the database and if so it checks the password to complete login process.
     * else, it creates a new Player with the name.
     * @param name
     * @param password
     * @return 1-logged in; 2-password incorrect;
     */
    public int loginProcess(String name, String password){
        
        return 0;
    }
    
    /** 
     * The method adds player to system.
     * @param name
     * @param password
     * @return true if add successful
     */
    @Override
    public Player addPlayer(String name, String password) {
        
        if(db.getPlayers().containsKey(name)) //chek if player exist
        {
            if(password==db.getPlayers().get(name).password) //chek if password is correct
            {
                System.out.println("Welcome back " + name + " , We're glad to have you (and especially your money) back!");
                return db.getPlayers().get(name);
            }
            else
            {
                System.out.println("Worng password or player name is already exist, try again");
                return null;
            }
        }
        else
        {
            Player p = new Player(name, password);
            db.getPlayers().put(name, p);
            System.out.println("Welcome " + name + " , We are glad you chose to play blackjack with BGS (and spend all your money here)");
            return p;
        }
        
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
