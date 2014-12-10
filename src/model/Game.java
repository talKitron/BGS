package model;

/**
 * Blackjack Game representation class.
 * @author BGS Team
 */
public class Game {
    /**
     * Current Game's Deck of Cards.
     */
    Deck deck;
    /**
     * Current Game's Player.
     */
    Player player;
    /**
     * Current Hand for the Dealer.
     */
    Hand dealer;
    
    /**
     * Constructor for Card.
     * @param player
     */
    public Game(Player player){
        this.deck = new Deck();
        this.player = player;
        dealer = new Hand();
    }
    
     /**
     * @return the deck
     */
    public Deck getDeck(){
        return this.deck;
    }
    
     /**
     * @return the player
     */
    public Player getPlayer(){
        return this.player;
    }
    
     /**
     * @return the dealer
     */
    public Hand getDealer(){
        return this.dealer;
    }
    
    /**
     * Occurs after clicking on the "Deal" button, deals two cards for player and dealer
     */
    protected void deal() {
        deck.shuffle();
        
        /*for( int i =0 ; i<52 ; i++) // printing the deck for testing
            System.out.println(deck.getCardDeck()[i]);*/
        
        System.out.println(player.name + " cards:");
        Card card = deck.dealNextCard();
        player.currentHand.cards[0] = card; //deals player first card
        System.out.println(card); //print player first card
        
        card = deck.dealNextCard();
        player.currentHand.cards[1] = card; //deals player second card
        System.out.println(card); //print player second card
        
        System.out.println("Dealer cards:");
        card = deck.dealNextCard();
        dealer.cards[0] = card; //deals dealer first card
        System.out.println("Hidden card");
        
        card = deck.dealNextCard();
        dealer.cards[1] = card; //deals dealer second card
        System.out.println(card); //print dealer second card
    }
}
