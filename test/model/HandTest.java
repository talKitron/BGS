package model;

import static model.Suit.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Roie
 */
public class HandTest {

    public HandTest() {
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
     * Test of resetHand method, of class Hand.
     */
    @Test
    public void testResetHand() {
        System.out.println("Testing resetHand() Method...");
        Hand instance = new Hand();
        Card[] cards = new Card[11];
        cards[0] = new Card(2, Spades);
        cards[1] = new Card(13, Hearts);
        cards[2] = new Card(2, Clubs);
        cards[3] = new Card(8, Spades);
        cards[4] = new Card(4, Clubs);
        cards[5] = new Card(6, Diamonds);
        cards[6] = new Card(7, Spades);
        cards[7] = new Card(11, Hearts);
        cards[8] = new Card(12, Diamonds);
        cards[9] = new Card(2, Clubs);
        cards[10] = null;                   //marks end of array

        instance.setCardsDebug(cards);             //every cell in cards[] should contain a card
        for (int i = 0; i < 10; i++) {
            assertNotNull(instance.getCards()[i]);
        }

        instance.resetHand();                       //now very cell in cards[] should be null

        for (int i = 0; i < 10; i++) {
            assertNull(instance.getCards()[i]);
        }
    }

    /**
     * Test of dealerHandValue method, of class Hand.
     */
    @Test
    public void testDealerHandValue() {
        System.out.println("dealerHandValue");
        Hand instance = new Hand();
        Card[] cards = new Card[11];
        cards[0] = new Card(2, Spades);
        cards[1] = new Card(13, Hearts);
        cards[2] = new Card(2, Clubs);
        cards[3] = new Card(8, Spades);
        cards[4] = new Card(4, Clubs);
        cards[5] = new Card(6, Diamonds);
        cards[6] = new Card(7, Spades);
        cards[7] = new Card(11, Hearts);
        cards[8] = new Card(12, Diamonds);
        cards[9] = new Card(2, Clubs);
        cards[10] = null;                   //marks end of array

        instance.setCardsDebug(cards);
        int expResult = 61;
        int result = instance.dealerHandValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of playerHandValue method, of class Hand.
     */
    @Test
    public void testPlayerHandValue() {
        System.out.println("playerHandValue");
        Hand instance = new Hand();
        Card[] cards = new Card[11];
        cards[0] = new Card(4, Spades);
        cards[1] = new Card(13, Hearts);
        cards[2] = new Card(5, Clubs);
        cards[3] = new Card(8, Spades);
        cards[4] = new Card(2, Clubs);
        cards[5] = new Card(3, Diamonds);
        cards[6] = new Card(4, Spades);
        cards[7] = new Card(11, Hearts);
        cards[8] = new Card(7, Diamonds);
        cards[9] = new Card(9, Clubs);
        cards[10] = null;                   //marks end of array

        instance.setCardsDebug(cards);
        int expResult = 62;
        int result = instance.playerHandValue();
        assertEquals(expResult, result);
    }

}
