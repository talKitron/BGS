package model;

import java.util.Date;
import utilities.Constants;

/**
 * The class represents a Player in the system.
 * @author BGS Team
 */
public class Player {
    /**player name*/
    String name;
    /**player password*/
    String password;
    /**player login date*/
    Date loginDate;
    /**how much money the player have*/
    int bank;
    /**the cards that the player currently holds in is hand*/
    Hand currentHand;
    /**
     * Constructor of Player.
     * @param name 
     * @param password
     */
    public Player( String name, String password){
        this.name = name;
        this.password = password;
        this.loginDate = new Date();
        this.bank = Constants.STARTING_AMOUNT;
        currentHand = new Hand();
    }
     /**
     * @return the name
     */
    protected String getName(){
        return this.name;
    }
     /**
     * @return the password
     */
    protected String getPassword(){
        return this.password;
    }
     /**
     * @return the loginDate
     */
    protected Date getLoginDate(){
        return this.loginDate;
    }
     /**
     * @return the bank
     */
    protected int getBank(){
        return this.bank;
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
}
