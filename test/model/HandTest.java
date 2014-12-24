/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * Test of getNextIndex method, of class Hand.
     */
    @Test
    public void testGetNextIndex() {
        System.out.println("getNextIndex");
        Hand instance = new Hand();
        int expResult = 0;
        int result = instance.getNextIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNextIndex method, of class Hand.
     */
    @Test
    public void testSetNextIndex() {
        System.out.println("setNextIndex");
        int nextIndex = 0;
        Hand instance = new Hand();
        instance.setNextIndex(nextIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCards method, of class Hand.
     */
    @Test
    public void testGetCards() {
        System.out.println("getCards");
        Hand instance = new Hand();
        Card[] expResult = null;
        Card[] result = instance.getCards();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSoft method, of class Hand.
     */
    @Test
    public void testIsSoft() {
        System.out.println("isSoft");
        Hand instance = new Hand();
        boolean expResult = false;
        boolean result = instance.isSoft();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSoft method, of class Hand.
     */
    @Test
    public void testSetSoft() {
        System.out.println("setSoft");
        boolean soft = false;
        Hand instance = new Hand();
        instance.setSoft(soft);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetHand method, of class Hand.
     */
    @Test
    public void testResetHand() {
        System.out.println("resetHand");
        Hand instance = new Hand();
        Card[] cards = new Card[11];
        cards[0] = new Card(2,Spades);
        cards[1] = new Card(13,Hearts);
        cards[2] = new Card(2,Clubs);
        cards[3] = new Card(8,Spades);
        cards[4] = new Card(4,Clubs);
        cards[5] = new Card(6,Diamonds);
        cards[6] = new Card(7,Spades);
        cards[7] = new Card(11,Hearts);
        cards[8] = new Card(12,Diamonds);
        cards[9] = new Card(2,Clubs);
        cards[10] = new Card(3,Hearts);

        instance.setCards_debug(cards);
        for (int i=0; i<11; i++){
            assertNotNull(instance.getCards()[i]);
        }
        
        instance.resetHand();                       //now all Cards[] should be null
        
        for (int i=0; i<11; i++){
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
        int expResult = 0;
        int result = instance.dealerHandValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerHandValue method, of class Hand.
     */
    @Test
    public void testPlayerHandValue() {
        System.out.println("playerHandValue");
        Hand instance = new Hand();
        int expResult = 0;
        int result = instance.playerHandValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
