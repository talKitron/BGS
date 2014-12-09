package model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import utilities.Constants;

/**
 * Database class - Holds BGS data.
 * @author BGS Team
 */
public final class DataBase implements java.io.Serializable {
    //***************************************** Variables *********************************************

    /**
     * Singleton instance of this class, loaded on the first execution of SysData.getInstance()
     */
    private static DataBase instance;
    /**
     * Boolean flag for class instance existence (singleton)
     */
    private static boolean exists = false;
    /**
     * Stores all the players
     */
    private HashMap<String, Player> players;
    /**
     * Stores all the games
     */
    private HashSet<Game> games;

   

    //***************************************** Constructors ******************************************
    private DataBase() {
        players = new HashMap<>();
        games = new HashSet<>();

        executeInput();
    }
    //***************************************** Getters & Setters *************************************

    /**
     * @return the players
     */
    protected HashMap<String, Player> getPlayers() {
        return players;
    }
    /**
     * @return the games
     */
    protected HashSet<Game> getGames() {
        return games;
    }

    //***************************************** Methods ***********************************************

    protected static void setInstance(DataBase sysData) {
        instance = sysData;
    }

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    protected static DataBase getInstance() {
           executeInput();
        if (!exists) {
            exists = true;
            instance = new DataBase();
            return instance;
        }
        return instance;
    }
    /**
     * the methods execute the input from file.
     */
    public static void executeInput() {
        try {
            FileInputStream fileIn = new FileInputStream("BGS.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            setInstance(((DataBase) in.readObject()));
            exists = true;
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            if (Constants.DEBUG){
                System.out.println(e.getMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            if (Constants.DEBUG){
                 System.out.println(e.getMessage());
            }
        }
    }
    /**
     * the methods execute the output to file.
     * @param logOut
     */
    protected void executeOutput(boolean logOut) {

        try {
            instance.players = getPlayers();
            instance.games = getGames();
            FileOutputStream fileOut = new FileOutputStream("BGS.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(instance);
            out.close();
            fileOut.close();
            if (!logOut) {
                System.exit(0);
            }
        } catch (IOException e) {
            if (Constants.DEBUG){
                 System.out.println(e.getMessage());
            }
        }
    }
}
