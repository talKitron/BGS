package utilities;

/**
 *
 * @author BGS Team
 */
public final class Constants {
    
    /**Debug mode for errors*/
    public static final boolean DEBUG = true;
    /**number of cards in deck*/
    public static final int CARDS_IN_DECK = 52;
    /**The maximum number for win*/
    public static final int BLACKJACK = 21;
    
     public static final int STARTING_AMOUNT = 0;
    /**The maximum possible cards in a hand (Dealer: 4 aces, 4 2's, 3 3's (4*1 + 4*2 + 3*3 = 21)*/
    //public static final int MAX_CARDS_IN_HAND = 11;
    /**The amount Dealers stands on*/
    public static final int DEALER_STAND = 17;
    /**Dealer Hand x axis*/
    public static final int DealerX = 30;
    /**Dealer Hand y axis*/
    public static final int DealerY = 130;
    /**Player Hand x axis*/
    public static final int PlayerX = 555;
    /**Player Hand y axis*/
    public static final int PlayerY = 438;
    /**Deck of Cards x axis*/
    public static final int DeckX = 1000;
    /**Deck of Cards y axis*/
    public static final int DeckY = 60;
    /**Deck of Cards dealing speed: Fast*/
    public static final int DECK_DEAL_FAST = 1;
    /**Deck of Cards dealing speed: Slow*/
    public static final int DECK_DEAL_SLOW = 1;
}
