package model;

import static model.Suit.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BGS Team
 */
public class GameTest {

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getWins method, of class Game.
     */
    @Test
    public void testGetWins() {
        System.out.println("Testing getWins() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        instance.setWins(9);
        int expResult = 9;
        int result = instance.getWins();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWins method, of class Game.
     */
    @Test
    public void testSetWins() {
        System.out.println("Testing setWins() Method...");
        int wins = 7;
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        instance.setWins(wins);
        int expResult = 7;
        int result = instance.getWins();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoses method, of class Game.
     */
    @Test
    public void testGetLoses() {
        System.out.println("Testing getLoses() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        instance.setLoses(12);
        int expResult = 12;
        int result = instance.getLoses();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoses method, of class Game.
     */
    @Test
    public void testSetLoses() {
        System.out.println("Testing setLoses() Method...");
        int loses = 15;
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        instance.setLoses(loses);
        int expResult = 15;
        int result = instance.getLoses();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeck method, of class Game.
     */
    @Test
    public void testGetDeck() {
        System.out.println("Testing getDeck() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        Deck result = instance.getDeck();
        assertTrue(result instanceof Deck);
    }

    /**
     * Test of getPlayer method, of class Game.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("Testing getPlayer() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        Player expResult = testPlayer;
        Player result = instance.getPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDealer method, of class Game.
     */
    @Test
    public void testGetDealer() {
        System.out.println("Testing getDealer() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        Hand result = instance.getDealer();
        assertTrue(result instanceof Hand);
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testGetScore() {
        System.out.println("Testing getScore() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        instance.setScore(724);
        int expResult = 724;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class Game.
     */
    @Test
    public void testSetScore() {
        System.out.println("Testing setScore() Method...");
        int score = 32423;
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        instance.setScore(score);
        int expResult = 32423;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRound method, of class Game.
     */
    @Test
    public void testGetRound() {
        System.out.println("Testing getRound() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        instance.setRound(6);
        int expResult = 6;
        int result = instance.getRound();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRound method, of class Game.
     */
    @Test
    public void testSetRound() {
        System.out.println("Testing setRound() Method...");
        int round = 15;
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        instance.setRound(round);
        int expResult = 15;
        int result = instance.getRound();
        assertEquals(expResult, result);
    }

    /**
     * Test of resetGame method, of class Game. This test need testResetHand and testShuffle to pass in order to pass
     */
    @Test
    public void testResetGame() {
        System.out.println("Testing resetGame() Method...");
        HandTest handTest = new HandTest();
        DeckTest deckTest = new DeckTest();
        handTest.testResetHand();
        deckTest.testShuffle();
    }

    /**
     * Test of deal method, of class Game.
     */
    @Test
    public void testDeal() {
        System.out.println("Testing deal() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        Card playerCard1 = testPlayer.getCurrentHand().getCards()[0];
        Card playerCard2 = testPlayer.getCurrentHand().getCards()[1];
        Card dealerCard1 = instance.getDealer().getCards()[0];
        Card dealerCard2 = instance.getDealer().getCards()[0];

        assertTrue(playerCard1 == null);
        assertTrue(playerCard2 == null);
        assertTrue(dealerCard1 == null);
        assertTrue(dealerCard2 == null);

        instance.deal();

        playerCard1 = testPlayer.getCurrentHand().getCards()[0];
        playerCard2 = testPlayer.getCurrentHand().getCards()[1];
        dealerCard1 = testPlayer.getCurrentHand().getCards()[0];
        dealerCard2 = testPlayer.getCurrentHand().getCards()[1];

        assertTrue(playerCard1 != null);
        assertTrue(playerCard2 != null);
        assertTrue(dealerCard1 != null);
        assertTrue(dealerCard2 != null);
    }

    /**
     * Test of hit method, of class Game.
     */
    @Test
    public void testHit() {
        System.out.println("Testing hit() Method...");
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        Card playerCard1 = testPlayer.getCurrentHand().getCards()[0];
        assertTrue(playerCard1 == null);
        instance.deal();
        playerCard1 = testPlayer.getCurrentHand().getCards()[0];
        assertTrue(playerCard1 != null);
    }

    /**
     * Test of isBusted method, of class Game.
     */
    @Test
    public void testIsBusted() {
        System.out.println("Testing isBusted() Method...");
        Card[] cards = new Card[11];
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);

        cards[0] = new Card(8, Spades);
        cards[1] = new Card(11, Hearts);
        cards[2] = new Card(2, Diamonds);
        instance.getPlayer().getCurrentHand().setCardsDebug(cards);
        boolean result1 = instance.isBusted();

        cards[0] = new Card(8, Spades);
        cards[1] = new Card(13, Hearts);
        cards[2] = new Card(4, Diamonds);
        instance.getPlayer().getCurrentHand().setCardsDebug(cards);
        boolean result2 = instance.isBusted();

        boolean expResult1 = false;
        boolean expResult2 = true;

        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of calculateScore method, of class Game.
     */
    @Test
    public void testCalculateScore() {
        System.out.println("Testing calculateScore() Method...");
        Card[] cards = new Card[11];
        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        cards[0] = new Card(8, Spades);
        cards[1] = new Card(11, Hearts);
        cards[2] = new Card(2, Diamonds);
        instance.getPlayer().getCurrentHand().setCardsDebug(cards);
        instance.setRound(2);
        int expResult1 = 60;
        int result1 = instance.calculateScore();
        assertEquals(expResult1, result1);

        instance.setRound(3);
        int expResult2 = 40;
        int result2 = instance.calculateScore();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of playerWin method, of class Game.
     */
    @Test
    public void testPlayerWin() {
        System.out.println("Testing playerWin() Method...");
        int score = 100;
        int wins = 2;
        Card[] cards = new Card[11];

        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        cards[0] = new Card(8, Spades);
        cards[1] = new Card(11, Hearts);
        cards[2] = new Card(2, Diamonds);
        instance.setScore(score);
        instance.setWins(wins);
        instance.getPlayer().getCurrentHand().setCardsDebug(cards);
        instance.playerWin();
        assertTrue(score < instance.getScore());
        assertThat(instance.getWins(), is(3));
    }

    /**
     * Test of playerLose method, of class Game.
     */
    @Test
    public void testPlayerLose() {
        System.out.println("Testing playerLose() Method...");
        int score = 100;
        int loses = 2;
        Card[] cards = new Card[11];

        Player testPlayer = new Player("elad", "123");
        Game instance = new Game(testPlayer);
        cards[0] = new Card(8, Spades);
        cards[1] = new Card(11, Hearts);
        cards[2] = new Card(2, Diamonds);
        instance.setScore(score);
        instance.setLoses(loses);
        instance.getPlayer().getCurrentHand().setCardsDebug(cards);
        instance.playerLose();
        assertTrue(score > instance.getScore());
        assertThat(instance.getLoses(), is(3));
    }

    /**
     * Test of whoWon method, of class Game.
     */
    @Test
    public void testWhoWon() {
        System.out.println("Testing whoWon() Method...");
        Player testPlayer = new Player("elad", "123");
        Hand testDealer = new Hand();
        int playerValue = 0;
        int dealerValue = 0;
        Game instance = new Game(testPlayer);
        Card[] playerCards = new Card[11];
        Card[] dealerCards = new Card[11];

        playerCards[0] = new Card(8, Spades);      //player cards
        playerCards[1] = new Card(11, Hearts);
        playerCards[2] = new Card(2, Diamonds);
        instance.getPlayer().getCurrentHand().setCardsDebug(playerCards);

        dealerCards[0] = new Card(8, Spades);      //dealer cards
        dealerCards[1] = new Card(11, Hearts);
        dealerCards[2] = new Card(4, Diamonds);
        testDealer.setCardsDebug(dealerCards);
        instance.setDealerDebug(testDealer);

        playerValue = instance.getPlayer().getCurrentHand().playerHandValue();  //check value using breakpoint
        dealerValue = instance.getDealer().dealerHandValue();                   //check value using breakpoint

        boolean expResult1 = true;     // 1st case: dealer's hand > 21, player should win
        boolean result1 = instance.whoWon();
        assertEquals(expResult1, result1);

        playerCards[0] = new Card(7, Spades);      //player cards
        playerCards[1] = new Card(9, Hearts);
        playerCards[2] = new Card(3, Diamonds);
        instance.getPlayer().getCurrentHand().setCardsDebug(playerCards);

        dealerCards[0] = new Card(8, Spades);      //dealer cards
        dealerCards[1] = new Card(2, Hearts);
        dealerCards[2] = new Card(4, Diamonds);
        testDealer.setCardsDebug(dealerCards);
        instance.setDealerDebug(testDealer);

        playerValue = instance.getPlayer().getCurrentHand().playerHandValue();  //check value using breakpoint
        dealerValue = instance.getDealer().dealerHandValue();                   //check value using breakpoint

        boolean expResult2 = true;     // 2nd case: player's hand > dealer's hand, player should win
        boolean result2 = instance.whoWon();
        assertEquals(expResult2, result2);

        playerCards[0] = new Card(9, Spades);      //player cards
        playerCards[1] = new Card(2, Hearts);
        playerCards[2] = new Card(3, Diamonds);
        instance.getPlayer().getCurrentHand().setCardsDebug(playerCards);

        dealerCards[0] = new Card(8, Spades);      //dealer cards
        dealerCards[1] = new Card(12, Hearts);
        dealerCards[2] = new Card(2, Diamonds);
        testDealer.setCardsDebug(dealerCards);
        instance.setDealerDebug(testDealer);

        playerValue = instance.getPlayer().getCurrentHand().playerHandValue();  //check value using breakpoint
        dealerValue = instance.getDealer().dealerHandValue();                   //check value using breakpoint

        boolean expResult3 = false;     // 3rd case: dealer's hand > player's hand and dealer's hand < 21, player should lose
        boolean result3 = instance.whoWon();
        assertEquals(expResult3, result3);
    }

//    /**
//     * Test of stand method, of class Game.
//     */
//    @Ignore("needs to be adjust to 3rd iterarion changes")
//    @Test
//    public void testStand() {
//        System.out.println("stand");
//        Hand testDealer = new Hand();
//        Card[] dealerCards = new Card[11];
//        Player testPlayer = new Player("elad", "123");
//        Game instance = new Game(testPlayer);
//
//        dealerCards[0] = new Card(7, Spades);      //dealer cards
//        dealerCards[1] = new Card(7, Hearts);
//        dealerCards[2] = new Card(2, Diamonds);
//
//        assertTrue(dealerCards[3] == null);         //make sure dealer has only 3 cards
//        testDealer.setNextIndex(3);
//        testDealer.setCardsDebug(dealerCards);
//        instance.setDealerDebug(testDealer);
//
//        instance.stand();
//        assertTrue(instance.getDealer().getCards()[3] != null);   //make sure dealer took a card
//
//        instance.stand();
//        assertTrue(instance.getDealer().getCards()[4] == null);   //dealer has 17 cards. make sure he doesn't take another card.
//    }
}
