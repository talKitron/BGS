package model;
import utilities.Constants;

/**
 * Blackjack Game representation class.
 * @author BGS Team
 */
public class Game implements java.io.Serializable {
    /**
     * Current Game's Deck of Cards.
     */
    private Deck deck;
    /**
     * Current Game's Player.
     */
    private Player player;
    /**
     * Current Hand for the Dealer.
     */
    private Hand dealer;
    /**
     * Current Game's bet.
     */
    private int bet;
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
     * @return the bet
     */
    public int getBet() {
        return bet;
    }
    /**
     * sets game's bet.
     * @param bet 
     */
    public void setBet(int bet) {
        this.bet = bet;
    }
    /**
    * reset game: reset dealer hand, player hand and shuffle deck.
    */
    public void resetGame() {
        
        dealer.resetHand();
        player.getCurrentHand().resetHand();
        deck.shuffle();
    }
    /**
     * Occurs after clicking on the "Deal" button, deals two cards for player and dealer
     */
    protected void deal() {
        deck.shuffle();
        
        System.out.println(player.getName() + " cards:");
        Card card = deck.dealNextCard();
        player.getCurrentHand().getCards()[0] = card; //deals player first card
        System.out.println(card); //print player first card
        
        card = deck.dealNextCard();
        player.getCurrentHand().getCards()[1] = card; //deals player second card
        System.out.println(card); //print player second card
        
        System.out.println("Dealer cards:");
        card = deck.dealNextCard();
        dealer.getCards()[0] = card; //deals dealer first card
        System.out.println("Hidden card");
        
        card = deck.dealNextCard();
        dealer.getCards()[1] = card; //deals dealer second card
        System.out.println(card); //print dealer second card
    }
    /**
     * Occurs after clicking on the "Hit" button, deals one card for player
     */
    public void hit() {
        Card card = deck.dealNextCard();
        int i = player.getCurrentHand().findNextFreeIndex();
        player.getCurrentHand().getCards()[i] = card;
        System.out.println(player.getName() + " next card is:");
        System.out.println(card);
    }
    /**
     * @return true if player hand is more than 21 and false if not
     */
    public boolean isBusted() {
         if(player.getCurrentHand().handValue()>Constants.BLACKJACK)
        {
           System.out.println("busted");
           System.out.println(player.getName() + ", lose");
           playerLose();
           return true; 
        }
        return false;
    }
    /**
     * Occurs when player win a round
     */
    public void playerWin() {
        player.setLoses(1+player.getLoses());
        player.setBank(player.getBank()-bet);
        resetGame();
    }
    /**
     * Occurs when player lose a round
     */
    public void playerLose() {
        player.setLoses(1+player.getLoses());
        player.setBank(player.getBank()-bet);
        resetGame();
    }
     /**
     * @return true if player win and false if not
     */
    public boolean whoWon() {
        if(dealer.handValue() > Constants.BLACKJACK || player.getCurrentHand().handValue()>dealer.handValue())
        {
            playerWin();
            System.out.println(player.getName() + ", win");
            return true;
        }
        else
        {
           playerLose(); 
           System.out.println(player.getName() + ", lose");
           return false;
        }
    }
    /**
     * Occurs after clicking on the "Stand" button, check dealer cards
     */
    public void stand() {
        
        while(dealer.handValue()<=17)
        {
            if(dealer.handValue()<17 || (dealer.handValue()==17 && dealer.isSoft()))
            {
                Card card = deck.dealNextCard();
                int i = dealer.findNextFreeIndex();
                dealer.getCards()[i] = card;
                System.out.println("Dealer next card is:");
                System.out.println(card);
            }
            else
            {
                return;
            }
        }  
    }
}
