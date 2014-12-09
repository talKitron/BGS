package model;

import java.io.IOException;
import java.util.Date;

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
     */
    public static Model getInstance(){
        if (!exists) {
           
            db = DataBase.getInstance();
            if (db != null) {
                DataBase.executeInput();
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
     * @param pass
     * @return 0-undefined error;1-logged in; 2-password incorrect; 3-name illegal
     */
    public int loginProcess(String name, String pass){
        if(db.getPlayers().containsKey(name)){ // player exists in the system
            if(db.getPlayers().get(name).getPassword().equals(pass)){ // if the pass is equal login else alert "pass isn't compatible"
                db.getPlayers().get(name).setLoginDate(new Date());
                return 1; // login successfull
            }
            else return 2; // password incorrect!
        }
        if (addPlayer(name, pass) == null)
            return 1; // new player created
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
    
    /**
     * Gets the current Player, creates a new Game for him (also dealing the initial cards) and adds it to the Database
     * @param player
     * @return the newly created Game, current Game
     */
    public Game deal(Player player) {
        Game currentGame = new Game(player);
        db.getGames().add(currentGame);
        currentGame.deal();
        return currentGame;
    }
}
