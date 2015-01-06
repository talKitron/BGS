package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class represents a Player in the system.
 *
 * @author BGS Team
 */
public class Player implements java.io.Serializable {

    /**
     * player name
     */
    private final String name;
    /**
     * player password
     */
    private String password;
    /**
     * player login date
     */
    private Date loginDate;
    /**
     * the cards that the player currently holds in is hand
     */
    private Hand currentHand;
    /**
     * the Player\'s image path
     */
    private String imagePath;
    /**
     * Serializable object version number.
     */
    private static final long serialVersionUID = 47L;

    /**
     * Constructor of Player.
     *
     * @param name
     * @param password
     */
    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.loginDate = new Date();
        currentHand = new Hand();
        imagePath = "";
    }

    /**
     * sets Player's password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * sets last loginDate of Player.
     *
     * @param loginDate
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * sets currentHand.
     *
     * @param currentHand
     */
    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }

    /**
     * sets Player image\'s path.
     *
     * @param newImagePath
     */
    public void setImagePath(String newImagePath) {
        this.imagePath = newImagePath;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the loginDate
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * @return the current Hand
     */
    public Hand getCurrentHand() {
        return currentHand;
    }

    /**
     * @return Player image\'s path.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * The method compares given object to this Airline (key fields).
     *
     * @return true/false if equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            return name.equals(((Player) obj).name);
        }
        return false;
    }

    /**
     * The method hashing key fields.
     *
     * @return int hash code result.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 7;
        return (prime * result + name.hashCode());
    }

    /**
     * @return String representation of the Player
     */
    @Override
    public String toString() {
        return ("Player: " + name + " Last seen: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(loginDate) + ".");
    }
}
