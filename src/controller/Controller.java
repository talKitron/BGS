package controller;

import java.io.IOException;
import model.Card;
import model.Game;
import model.Model;
import model.Player;
import view.View;

/**
 * Controller Logic class.
 *
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
     * The method creates this class\'s instance & provides access to it, by returning a reference (singleton).
     *
     * @return reference to this class\'s only instance, or null if reference was already returned (singleton).
     */
    public static Controller getInstance() {
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
     * Checks if such name already exists in the database and if so it checks the password to complete login process. else, it creates a new Player with the name.
     *
     * @param name
     * @param password
     * @return 1-logged in; 2-password incorrect; 3-name illegal
     */
    public int loginProcess(String name, String password) {
        return model.loginProcess(name, password);
    }

    /**
     * The method adds player to system.
     *
     * @param name
     * @param password
     * @return true if add successful
     */
    public Player addPlayer(String name, String password) {
        return model.addPlayer(name, password);
    }

    /**
     * The method creates a new Game in the system.
     *
     * @param player
     * @return newly created Game
     */
    public Game addGame(Player player) {
        return model.addGame(player);
    }

    /**
     * Occurs after clicking on the "Deal" button, deals two cards for player and dealer.
     *
     * @param game
     */
    public void deal(Game game) {
        model.deal(game);
    }

    /**
     * Occurs after clicking on the "Hit" button, deals one card for player
     *
     * @param game
     */
    public void hit(Game game) {
        model.hit(game);
    }

    /**
     * @param game
     * @return true if player hand is more than 21 and false if not
     */
    public boolean isBusted(Game game) {
        return model.isBusted(game);
    }

    /**
     * Occurs after clicking on the "Stand" button, check dealer cards
     *
     * @param game
     * @return The last Card dealt to Dealer
     */
    public Card stand(Game game) {
        return model.stand(game);
    }

    /**
     * @param game
     * @return true if player win and false if not
     */
    public boolean whoWon(Game game) {
        return model.whoWon(game);
    }

    /**
     * @return currentPlayer
     */
    public Player getCurrentPlayer() {
        return model.getCurrentPlayer();
    }

    /**
     * @return a random fact about Blackjack
     */
    public String getFact() {
        return model.getFact();
    }

    /**
     * @return BGS rules.
     */
    public String[] getRules() {
        return model.getRules();
    }

    /**
     * The method bills the Player for fun.
     *
     * @param game
     * @param drinkName
     */
    public void orderDrink(Game game, String drinkName) {
        model.orderDrink(game, drinkName);
    }

    /**
     * @return game with high score
     */
    public Game getHighScoreGame() {
        return model.getHighScoreGame();
    }

    /**
     * @return game with high win
     */
    public Game getHighWinsGame() {
        return model.getHighWinsGame();
    }

    /**
     * @return game with high lose
     */
    public Game getHighLosesGame() {
        return model.getHighLosesGame();
    }

    /**
     * The method handle the exit from system.
     *
     * @param logOut
     * @throws java.io.IOException
     */
    public void executeSysExit(boolean logOut) throws IOException {
        model.executeSysExit(logOut);
    }
}
