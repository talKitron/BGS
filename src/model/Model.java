package model;

import java.util.Date;

/**
 * Model Logic class
 *
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
    /**
     * Serializable object version number.
     */
    private static final long serialVersionUID = 46L;

    //***************************************** Constructors ******************************************
    private Model() {

    }
    //***************************************** Methods ***********************************************

    /**
     * The method creates the class instance & provides access to it, by returning a reference (singleton).
     *
     * @return reference to the class only instance, or null if reference was already returned (singleton).
     */
    public static Model getInstance() {
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
     *
     * @param exit
     */
    public void executeSysExit(boolean exit) {
        db.executeOutput(exit);
    }

    /**
     * Checks if such name already exists in the database and if so it checks the password to complete login process. else, it creates a new Player with the name.
     *
     * @param name
     * @param pass
     * @return 0-undefined error; 1-logged in; 2-password incorrect; 3-name illegal
     */
    @Override
    public int loginProcess(String name, String pass) {
        if (db.getPlayers().containsKey(name)) { // player exists in the system
            if (db.getPlayers().get(name).getPassword().equals(pass)) { // if the pass is equal login else alert "pass isn't compatible"
                db.getPlayers().get(name).setLoginDate(new Date());
                currentPlayer = db.getPlayers().get(name);
                return 1; // login successfull
            } else {
                return 2; // password incorrect!
            }
        } else if (addPlayer(name, pass) != null) {
            return 1; // new player created
        }
        return 0;
    }

    /**
     * The method adds player to system.
     *
     * @param name
     * @param password
     * @return the newly added Player if added successful
     */
    @Override
    public Player addPlayer(String name, String password) {
        Player newPlayer = new Player(name, password);
        if (db.getPlayers().put(name, newPlayer) == null) {
            System.out.println("Welcome " + name + ", we're glad you chose to play Blackjack with BGS (and spend all your money here!)");
            currentPlayer = newPlayer;
            return newPlayer;
        } else {
            return null;
        }
    }

    /**
     * The method creates a new Game in the system.
     *
     * @param player
     * @return newly created Game
     */
    @Override
    public Game addGame(Player player) {
        if (player != null) {
            Game game = new Game(player);
            if (!db.getGames().contains(game)) {
                db.getGames().add(game);
                return game;
            }
        }
        return null;
    }

    /**
     * Occurs after clicking on the "Deal" button, deals two cards for player and dealer.
     *
     * @param game
     */
    @Override
    public void deal(Game game) {
        game.deal();
    }

    /**
     * Occurs after clicking on the "Hit" button, deals one card for player.
     *
     * @param game
     */
    @Override
    public void hit(Game game) {
        game.hit();
    }

    /**
     * @param game
     * @return true if player hand is more than 21 and false if not
     */
    @Override
    public boolean isBusted(Game game) {
        return game.isBusted();
    }

    /**
     * Occurs after clicking on the "Stand" button, check dealer cards.
     *
     * @param game
     * @return Dealer latest dealt card
     */
    @Override
    public Card stand(Game game) {
        return game.stand();
    }

    /**
     * @param game
     * @return true if player win and false if not.
     */
    @Override
    public boolean whoWon(Game game) {
        return game.whoWon();
    }

    /**
     * @return currentPlayer.
     */
    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @return a random fact about Blackjack.
     */
    @Override
    public String getFact() {
        return db.getFacts()[(int) (Math.random() * 20)];
    }

    /**
     * @return BGS rules.
     */
    @Override
    public String[] getRules() {
        return db.getRules();
    }

    /**
     * The method bills the Player for fun.
     *
     * @param game
     * @param drinkName
     */
    public void orderDrink(Game game, String drinkName) {
        switch (drinkName) {
            case "Whiskey":
                game.setScore(game.getScore() - 8);
                break;
            default:
                break;
        }
    }

    /**
     * @return game with highest score.
     */
    @Override
    public Game getHighScoreGame() {
        Game highGame = null;
        int highScore = Integer.MIN_VALUE;
        for (Game g : db.getGames()) {
            if (g.getScore() >= highScore) {
                highGame = g;
                highScore = g.getScore();
            }
        }
        return highGame;
    }

    /**
     * @return game with most rounds won.
     */
    @Override
    public Game getHighWinsGame() {
        Game highGame = null;
        int highWins = 0;
        for (Game g : db.getGames()) {
            if (g.getWins() >= highWins) {
                highGame = g;
                highWins = g.getWins();
            }
        }
        return highGame;
    }

    /**
     * @return game with most rounds lost.
     */
    @Override
    public Game getHighLosesGame() {
        Game highGame = null;
        int highLoses = 0;
        for (Game g : db.getGames()) {
            if (g.getLoses() >= highLoses) {
                highGame = g;
                highLoses = g.getLoses();
            }
        }
        return highGame;
    }
}
