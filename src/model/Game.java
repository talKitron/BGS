package model;
import java.io.Serializable;
import utilities.Constants;

/**
 * Blackjack Game representation class.
 * @author BGS Team
 */
public class Game implements Serializable{
    /**
     * Current Game\'s Deck of Cards.
     */
    private final Deck deck;
    /**
     * Current Game\'s Player.
     */
    private final Player player;
    /**
     * Current Hand for the Dealer.
     */
    private Hand dealer;
    /**
     * Current Game\'s score.
     */
    private int score;
     /**
     * how much wins the player has
     */
    private int wins;
    /**
     * how much loses the player has
     */
    private int loses;
    /**
     * Current Game\'s round.
     */
    private int round;
    /**
     * Serializable object version number.
     */
    private static final long serialVersionUID = 44L;
    
    /**
     * Constructor for Card.
     * @param player
     */
    public Game(Player player){
        this.deck = new Deck();
        this.player = player;
        dealer = new Hand();
        score = 0;
        round = 0;
        wins = 0;
        loses = 0;
    }
    
     /**
     * @return the wins
     */
    public int getWins() {
        return wins;
    }
    
    /**
     * sets Player's wins.
     * @param wins 
     */
    public void setWins(int wins) {
        this.wins = wins;
    }
    
    /**
     * @return the loses
     */
    public int getLoses() {
        return loses;
    }
    
    /**
     * sets Player's loses.
     * @param loses 
     */
    public void setLoses(int loses) {
        this.loses = loses;
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
     * @return the score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * sets Game\'s score.
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * @return the round
     */
    public int getRound() {
        return round;
    }
    
    /**
     * sets game\'s round.
     * @param round 
     */
    public void setRound(int round) {
        this.round = round;
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
        
        round++;
        deck.shuffle();
        
        System.out.println(player.getName() + " cards:");
        Card card = deck.dealNextCard();
        player.getCurrentHand().getCards()[player.getCurrentHand().getNextIndex()] = card; //deals player first card
        player.getCurrentHand().setNextIndex(player.getCurrentHand().getNextIndex()+1);
        System.out.println(card); //print player first card
        
        card = deck.dealNextCard();
        player.getCurrentHand().getCards()[player.getCurrentHand().getNextIndex()] = card; //deals player second card
        player.getCurrentHand().setNextIndex(player.getCurrentHand().getNextIndex()+1);
        System.out.println(card); //print player second card

        card = deck.dealNextCard();
        dealer.getCards()[dealer.getNextIndex()] = card; //deals dealer first card
        dealer.setNextIndex(dealer.getNextIndex() + 1);
        System.out.println("Dealer cards:");
        System.out.println(card + " (Hidden card)");
        
        card = deck.dealNextCard();
        dealer.getCards()[dealer.getNextIndex()] = card; //deals dealer second card
        dealer.setNextIndex(dealer.getNextIndex() + 1);
        System.out.println(card); //print dealer second card
    }
    
    /**
     * Occurs after clicking on the "Hit" button, deals one card for player
     */
    public void hit() {
        Card card = deck.dealNextCard();
        player.getCurrentHand().getCards()[player.getCurrentHand().getNextIndex()] = card;
        player.getCurrentHand().setNextIndex(player.getCurrentHand().getNextIndex()+1);
        if (Constants.DEBUG){
            System.out.println(player.getName() + " next card is:");
        }
        System.out.println(card);
    }
    
    /**
     * @return true if player hand is more than 21 and false if not
     */
    public boolean isBusted() {
        if (player.getCurrentHand().playerHandValue() > Constants.BLACKJACK){
            if (Constants.DEBUG){
                System.out.println("BUSTED with " + player.getCurrentHand().playerHandValue());
                System.out.println("Dealer's with " + dealer.dealerHandValue());
            }
            return true; 
        }
        return false;
    }
    
    /**
    * calculate Score
     * @return player's score.
    */
    public int calculateScore() {
        if (round%2 == 0){
            return player.getCurrentHand().playerHandValue()*3;
        } else {
            return player.getCurrentHand().playerHandValue()*2;
        }
    } 
    
    /**
     * Occurs when player win a round
     */
    public void playerWin() {
        wins++;
        score += calculateScore();
        resetGame();
    }
    
    /**
     * Occurs when player lose a round
     */
    public void playerLose() {
        loses++;
        score -= calculateScore();
        resetGame();
    }
    
     /**
     * @return true if player win and false if not
     */
    public boolean whoWon() {
        if (player.getCurrentHand().playerHandValue() > Constants.BLACKJACK) {
            playerLose();
            if (Constants.DEBUG){
                System.out.println(player.getName() + ", lose");
            }
            return false; 
        } else if (dealer.dealerHandValue() > Constants.BLACKJACK || player.getCurrentHand().playerHandValue() > dealer.dealerHandValue()){
            playerWin();
            if (Constants.DEBUG){
                System.out.println(player.getName() + ", win");
            }
            return true;
        } else {
            playerLose(); 
            if (Constants.DEBUG){
                System.out.println(player.getName() + ", lose");
            }
            return false;
        }
    }
    
    /**
     * Occurs after clicking on the "Stand" button, check dealer cards
     * @return The last Card dealt to Dealer
     */
    public Card stand() {
        return deck.dealNextCard();
    }

    /**
     * Sets dealer. Used for GameTest unit test.
     * @param dealer the dealer to set
     */
    public void setDealerDebug(Hand dealer) {
        this.dealer = dealer;
    }    
}
