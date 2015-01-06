package model;

/**
 * Hand class to represent the current Hand of a Dealer or a Player.
 *
 * @author BGS Team
 */
public class Hand implements java.io.Serializable {

    /**
     * Hand\'s current Cards.
     */
    private Card[] cards;
    /**
     * card's next index.
     */
    private int nextIndex;
    /**
     * a flag that gives us an indication if the hand value is soft 17 or not
     */
    private boolean soft;
    /**
     * Serializable object version number.
     */
    private static final long serialVersionUID = 45L;

    /**
     * Constructor for Hand class.
     */
    public Hand() {
        cards = new Card[11];
        soft = false;
        nextIndex = 0;
    }

    /**
     * @return card's next index.
     */
    public int getNextIndex() {
        return nextIndex;
    }

    /**
     * sets nextIndex.
     *
     * @param nextIndex
     */
    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    /**
     * @return the cards.
     */
    public Card[] getCards() {
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
     *
     * @param soft
     */
    public void setSoft(boolean soft) {
        this.soft = soft;
    }

    /**
     * remove all cards from hand.
     */
    public void resetHand() {
        for (int i = 0; cards[i] != null; i++) {
            cards[i] = null;
            nextIndex = 0;
        }
    }

    /**
     * @return the dealer Hand value.
     */
    public int dealerHandValue() {

        int numOfAce = 0;//number of aces in hand
        int value = 0;// hand value
        soft = false;

        for (int i = 0; cards[i] != null; i++) {//run on hand's card
            if (cards[i].getValue() == 1) {//if card is Ace
                numOfAce++;
            } else {
                if (cards[i].getValue() >= 11) {//if card is jack, queen or king
                    value += 10;
                } else {
                    value += cards[i].getValue();//if card is beetween 2-10
                }
            }
        }

        for (int i = 0; i < numOfAce; i++) {//calculates the value of aces
            if (value + 11 > 21) {//if value + 11 is over 21 add 1 else add 11
                value += 1;
            } else {
                value += 11;
                soft = true;
            }
        }

        return value;
    }

    /**
     * @return the player Hand value.
     */
    public int playerHandValue() {

        int numOfAce = 0;//number of aces in hand
        int value = 0;// hand value

        for (int i = 0; cards[i] != null; i++) {//run on hand's card
            if (cards[i].getValue() == 1) {//if card is Ace
                numOfAce++;
            } else {
                if (cards[i].getValue() >= 11) {//if card is jack, queen or king
                    value += 10;
                } else {
                    value += cards[i].getValue();//if card is beetween 2-10
                }
            }
        }

        for (int i = 0; i < numOfAce; i++) {//calculates the value of aces
            if (i == 0) {//the first ace is 11, the others is 1
                value += 11;
            } else {
                value += 1;
            }
        }
        return value;
    }

    /**
     * Sets Cards. Used for GameTest & HandTest unit tests.
     *
     * @param cards the cards to set
     */
    public void setCardsDebug(Card[] cards) {
        this.cards = cards;
    }

}
