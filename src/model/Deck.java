package model;

import java.util.Random;
import utilities.Constants;


/**
 * Representation of the Deck of Cards.
 * @author BGS Team
 */
public class Deck {
    
    private Card[] cardDeck; // a cardarray that represent the deck
    private int numOfCard = Constants.CARDS_IN_DECK; //number of cards currently in the deck
    private int nextCardIndex;
    
    /**
     * Constructor of Card, creating a deck with 52 cards
     */
    public Deck(){
        
        cardDeck = new Card[Constants.CARDS_IN_DECK];
        nextCardIndex = 0;
        int cardIndex = 0;
        //for suit
        for(int i = 1 ; i<5 ; i++)
        {//for the value
            for(int j = 1 ; j<14 ; j++)
            {
                Suit suit=null;
                switch(i)
                {
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
                cardDeck[cardIndex]= new Card(j, suit);
                cardIndex++;
            }
        }
    }
    /**
     * @return the cardDeck
     */
    protected Card[] getCardDeck(){
        return this.cardDeck;
    }
    /**
     * @return the numOfCard
     */
    protected int getNumOfCard(){
        return this.numOfCard;
    }
    /**
     * @return the nextCardIndex
     */
    protected int getNextCardIndex(){
        return this.nextCardIndex;
    }
    /**
     * @param numOfCard
     */
    protected void setNumOfCard(int numOfCard){
        this.numOfCard=numOfCard;
    }
    /**
     * @return the next card in the deck
     */
    protected Card dealNextCard(){
        Card card = cardDeck[nextCardIndex];
        nextCardIndex++;
        return card;
    }
    protected void shuffle() {
        
        Random rand = new Random();
        
        for(int i = 0 ; i<cardDeck.length ; i++)
        {
            int r = rand.nextInt(Constants.CARDS_IN_DECK); //random card index for swap
            Card tempCard;
            //swap cards place's
            tempCard = cardDeck[i];
            cardDeck[i] = cardDeck[r];
            cardDeck[r] = tempCard;
        }
    }
}

