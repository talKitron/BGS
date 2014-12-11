package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import utilities.Constants;

/**
 * The class represents a Player in the system.
 * @author BGS Team
 */
public class Player implements java.io.Serializable {
    /**
     * player name
     */
    private String name;
    /**
     * player password
     */
    private String password;
    /**
     * player login date
     */
    private Date loginDate;
    /**
     * how much money the player have
     */
    private int bank;
    /**
     * how much wins the player has
     */
    private int wins;
    /**
     * how much loses the player has
     */
    private int loses;
    /**
     * the cards that the player currently holds in is hand
     */
    private Hand currentHand;
    
    /**
     * Constructor of Player.
     * @param name 
     * @param password
     */
    public Player(String name, String password){
        this.name = name;
        this.password = password;
        this.loginDate = new Date();
        this.bank = Constants.STARTING_AMOUNT;
        wins = 0;
        loses = 0;
        currentHand = new Hand();
    }
     /**
     * @return the wins
     */
    public int getWins() {
        return wins;
    }
    /**
     * sets Player's wins.
     * @param wins 
     */
    public void setWins(int wins) {
        this.wins = wins;
    }
    /**
     * @return the loses
     */
    public int getLoses() {
        return loses;
    }
    /**
     * sets Player's loses.
     * @param loses 
     */
    public void setLoses(int loses) {
        this.loses = loses;
    }
    /**
     * sets Player's password.
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * sets last loginDate of Player.
     * @param loginDate 
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    
    /**
     * sets Player's bank.
     * @param bank 
     */
    public void setBank(int bank) {
        this.bank = bank;
    }
    
    /**
     * sets currentHand.
     * @param currentHand 
     */
    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }
    
     /**
     * @return the name
     */
    public String getName(){
        return name;
    }
    
     /**
     * @return the password
     */
    public String getPassword(){
        return password;
    }
    
     /**
     * @return the loginDate
     */
    public Date getLoginDate(){
        return loginDate;
    }
    
     /**
     * @return the bank
     */
    public int getBank(){
        return bank;
    }
    
    /**
     * @return the current Hand
     */
    public Hand getCurrentHand(){
        return currentHand;
    }
    
    /**
     * The method compares given object to this Airline (key fields).
     * @return true/false if equal.
     */
    @Override
    public boolean equals(Object obj) {
		if(obj instanceof Player)
			return name.equals(((Player)obj).name);
		return false;
    }
    
    /**
    * The method hashing key fields.               
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
   public String toString(){
       return ("Player: " + name + " Last seen: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(loginDate) + " with " + bank + " in the bank.");
   }
}
