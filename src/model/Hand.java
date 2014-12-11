package model;

/**
 * Hand class to represent the current Hand of a Dealer or a Player.
 * @author BGS Team
 */
public class Hand implements java.io.Serializable {
    /**
     * Hand's current Cards.
     */
    private Card[] cards;
    /**
     * a flag that gives us an indication if the hand value is soft 17 or not
     */
    private boolean soft;
    /**
     * Constructor for Hand class.
     */
    public Hand() {
       cards = new Card[11];
       soft = false;
    }
    
    /**
     * @return the cards.
     */
    public Card[] getCards(){
        return this.cards;
    }
    /**
     * @return the soft.
     */
    public boolean isSoft() {
        return soft;
    }
    /**
     * sets soft.
     * @param soft 
     */
    public void setSoft(boolean soft) {
        this.soft = soft;
    }
    
    /**
     * remove all cards from hand.
     */
    public void resetHand() {
        
        for(int i = 0 ; cards[i]!=null ; i++)
        {
            cards[i] = null;
        }
    }
    /**
     * @return the next free index in the hand array
     */
    public int findNextFreeIndex()
    {
        for(int i = 1 ; cards[i]!=null ; )
        {
            i++;
            if( cards[i]==null)
            {
                return i;
            }
        }
        return -1;
    }
    /**
     * @return the Hand value.
     */
    public int handValue() {
        
        int numOfAce = 0;//number of aces in hand
        int value = 0;// hand value
        
        for(int i = 0; cards[i]!=null ; i++)//run on hand's card
        {
            if(cards[i].getValue()==1)//if card is Ace
            {
               numOfAce++; 
            }
            else
            {
                if(cards[i].getValue()>=11)//if card is jack, queen or king
                {
                    value += 10;
                }
                else
                {
                    value += cards[i].getValue();//if card is beetween 2-10
                }
            }
        }
        
        for(int i = 0; i<numOfAce ; i++)//calculates the value of aces
        {
            if(value+11 > 21)//if value + 11 is over 21 add 1 else add 11
            {
               value+=1; 
            }
            else
            {
                value+=11;
                soft = true;
            }
        }
        
        return value;
    }
}
