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
    /**
     * Holds current player details
     */
    public static Player currentPlayer;
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
            if (db != null){
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
     * @return 0-undefined error; 1-logged in; 2-password incorrect; 3-name illegal
     */
    public int loginProcess(String name, String pass){
        if(db.getPlayers().containsKey(name)){ // player exists in the system
            if(db.getPlayers().get(name).getPassword().equals(pass)){ // if the pass is equal login else alert "pass isn't compatible"
                db.getPlayers().get(name).setLoginDate(new Date());
                currentPlayer = db.getPlayers().get(name);
                return 1; // login successfull
            }
            else return 2; // password incorrect!
        } else if (addPlayer(name, pass) != null){
            return 1; // new player created
        }
        return 0; 
    }
    
    /** 
     * The method adds player to system.
     * @param name
     * @param password
     * @return the newly added Player if added successful
     */
    @Override
    public Player addPlayer(String name, String password) {
            Player newPlayer = new Player(name, password);
            if (db.getPlayers().put(name, newPlayer) == null){
                System.out.println("Welcome " + name + ", we're glad you chose to play Blackjack with BGS (and spend all your money here!)");
                currentPlayer = newPlayer;
                return newPlayer;
            } else {
                return null;
            }
    }
    
    /** 
    * The method adds player to system.
    * @param player
    * @return true if add successful
    */
    @Override
    public boolean addGame(Player player) {
        if (player != null){
            Game game = new Game (player);
            if (!db.getGames().contains(game)){
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
    
    /**
     * @return currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
