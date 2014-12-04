package model;

/**
 *
 * @author BGS Team
 */
public class Hand {
    
    Card[] cards;
    
    public Hand() {
       cards = new Card[11]; 
    }
    /**
     * @return the cards
     */
    protected Card[] getCards(){
        return this.cards;
    }
}
