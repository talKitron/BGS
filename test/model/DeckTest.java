/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class DeckTest {
    
    public DeckTest() {
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
     * Test of getCardDeck method, of class Deck.
     */
    @Test
    public void testGetCardDeck() {
        System.out.println("getCardDeck");
        Deck instance = new Deck();
        Card[] expResult = null;
        Card[] result = instance.getCardDeck();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumOfCard method, of class Deck.
     */
    @Test
    public void testGetNumOfCard() {
        System.out.println("getNumOfCard");
        Deck instance = new Deck();
        int expResult = 0;
        int result = instance.getNumOfCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextCardIndex method, of class Deck.
     */
    @Test
    public void testGetNextCardIndex() {
        System.out.println("getNextCardIndex");
        Deck instance = new Deck();
        int expResult = 0;
        int result = instance.getNextCardIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumOfCard method, of class Deck.
     */
    @Test
    public void testSetNumOfCard() {
        System.out.println("setNumOfCard");
        int numOfCard = 0;
        Deck instance = new Deck();
        instance.setNumOfCard(numOfCard);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dealNextCard method, of class Deck.
     */
    @Test
    public void testDealNextCard() {
        System.out.println("dealNextCard");
        Deck instance = new Deck();
        Card expResult = null;
        Card result = instance.dealNextCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shuffle method, of class Deck.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        Deck instance = new Deck();
        instance.shuffle();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Deck.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Deck instance = new Deck();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
