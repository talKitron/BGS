package model;

/**
 * Hand class to represent the current Hand of a Dealer or a Player.
 * @author BGS Team
 */
public class Hand implements java.io.Serializable {
    /**
     * Hand's current Cards.
     */
    Card[] cards;
    
    /**
     * Constructor for Hand class.
     */
    public Hand() {
       cards = new Card[11]; 
    }
    
    /**
     * @return the cards.
     */
    public Card[] getCards(){
        return this.cards;
    }
}
