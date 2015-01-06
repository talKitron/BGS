package model;

import java.util.Random;
import utilities.Constants;

/**
 * Representation of the Deck of Cards.
 *
 * @author BGS Team
 */
public class Deck implements java.io.Serializable {

    /**
     * Array of Cards to consist the Deck.
     */
    private Card[] cardDeck;
    /**
     * Holds current number of cards in the Deck.
     */
    private int numOfCard = Constants.CARDS_IN_DECK;
    /**
     * Holds the index for the next card.
     */
    private int nextCardIndex;
    /**
     * Serializable object version number.
     */
    private static final long serialVersionUID = 443L;

    /**
     * Constructor of Card, creating a deck with 52 cards
     */
    public Deck() {
        cardDeck = new Card[Constants.CARDS_IN_DECK];
        nextCardIndex = 0;
        int cardIndex = 0;
        //for suit
        for (int i = 1; i < 5; i++) { //for the value
            for (int j = 1; j < 14; j++) {
                Suit suit = null;

                switch (i) {
                    case 1:
                        suit = Suit.Clubs;
                        break;

                    case 2:
                        suit = Suit.Diamonds;
                        break;

                    case 3:
                        suit = Suit.Hearts;
                        break;

                    case 4:
                        suit = Suit.Spades;
                        break;
                }
                cardDeck[cardIndex] = new Card(j, suit);
                cardIndex++;
            }
        }
    }

    /**
     * @return the cardDeck
     */
    public Card[] getCardDeck() {
        return this.cardDeck;
    }

    /**
     * @return the numOfCard
     */
    public int getNumOfCard() {
        return this.numOfCard;
    }

    /**
     * @return the nextCardIndex
     */
    protected int getNextCardIndex() {
        return this.nextCardIndex;
    }

    /**
     * @param numOfCard
     */
    protected void setNumOfCard(int numOfCard) {
        this.numOfCard = numOfCard;
    }

    /**
     * @return the next card in the deck
     */
    protected Card dealNextCard() {
        Card card = cardDeck[nextCardIndex];
        nextCardIndex++;
        return card;
    }

    /**
     * The method shuffles the Deck of Cards.
     */
    protected void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < cardDeck.length; i++) {
            int rci = rand.nextInt(Constants.CARDS_IN_DECK); //random card index for swap
            Card tempCard;
            //swap cards place's
            tempCard = cardDeck[i];
            cardDeck[i] = cardDeck[rci];
            cardDeck[rci] = tempCard;
        }
        nextCardIndex = 0; //reset the next card index
    }

    @Override
    /**
     * String representation of Hand class.
     */
    public String toString() {
        String str = "";
        String newline = System.getProperty("line.separator");

        for (int i = 0; i < cardDeck.length; i++) { //printing the deck for testing
            if (i == cardDeck.length - 1) {
                str += String.valueOf(i + 1) + "." + " " + getCardDeck()[i];
                break;
            }
            str += String.valueOf(i + 1) + "." + " " + getCardDeck()[i] + newline;
        }

        return str;
    }
}
