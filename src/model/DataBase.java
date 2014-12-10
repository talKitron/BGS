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
    /**
     * Stores Blackjack facts for the MainFrame
     */
    private String[] facts = {
                                "Blackjack originated in French casinos around the 1700s where it was called \"vingt-et-un\" (twenty-and-one).",
                                "Emperor Napoleon played and enjoyed it more than any other card game.",
                                "There are approximately 140 countries in the world with casinos that offer blackjack.",
                                "The proper name of the game is \"21\"; \"blackjack\" was only added as slang in the 1920s.",
                                "The object of the game is not to get 21; the real object is to beat the dealer.",
                                "20 is the second best hand in the game.",
                                "16 is the worst hand in blackjack, followed by 15 as the second worst hand.",
                                "11 is the third best hand in blackjack.",
                                "Mathematically, in 100 hands the dealer will win 48 hands, the player will win 44 and there will be eight ties. Why does the dealer always win more hands? Because the dealer plays their hand LAST.",
                                "Casinos have the right to ask \"card counters\" and anyone else to leave the casino. It is based on the old English law that states, \"Management has the right to refuse service\".",
                                "Blackjack dealers only earn minimum wage, so tipping is very important for them in making a living.",
                                "A blackjack player has six options when deciding what action to take in any given hand: (1) hit, (2) stand, (3) split, (4) double down, (5) take insurance (6) surrender.",
                                "Mathematically, the dealer's average hand is a little better than 18.",
                                "Statistically, taking insurance is a sucker's bet. You'll lose more often than you win.",
                                "The blackjack shoe was introduced in Cuba during the 1950s.",
                                "Some casinos allow the dealer to hit a soft 17. You want the dealer to stay on ALL 17s.",
                                "Blackjack is a one-player game. The player's opponent is the game, not the dealer.",
                                "In blackjack, the most powerful card, the one that affects the deck the most, is the 5. Removing the 5 results in +0.64%, that is higher than any other card.",
                                "There are four times as many 10s in a blackjack deck as any other card.",
                                "House rules are important. They dictate what your advantage or disadvantage is.",
                                "It takes at least 7 shuffles to completely randomize a new deck of cards."
    };
   

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
    
    protected String[] getFacts(){
        return facts;
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
