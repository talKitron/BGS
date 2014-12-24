/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
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
     * Test of shuffle method, of class Deck.
     */
    @Test
    public void testShuffle() {
        System.out.println("Testing shuffle() Method...");
        Deck instance = new Deck();
        Deck testDeck = new Deck();
        int[] valueArray = new int[52];
        String[] suitArray = new String[52];
        int[] valueArray2 = new int[52];
        String[] suitArray2 = new String[52];
        
        for (int i=0; i<52; i++){                                       //copy original deck's cards values and suits to arrays for comparison later
            valueArray[i] = instance.getCardDeck()[i].getValue();
        }
        for (int i=0; i<52; i++){
            suitArray[i] = instance.getCardDeck()[i].getSuit().name();
        }
        for (int i=0; i<52; i++){                                       //copy test deck's cards values and suits to arrays for comparison later
            valueArray2[i] = testDeck.getCardDeck()[i].getValue();
        }
        for (int i=0; i<52; i++){
            suitArray2[i] = testDeck.getCardDeck()[i].getSuit().name();
        }
        
        assertArrayEquals(valueArray,valueArray2);                      //compare between original deck to test deck to make sure are equal
        assertArrayEquals(suitArray,suitArray2);
        
        instance.shuffle();                                             //shuffle the original deck
        
        for (int i=0; i<52; i++){                                       //copy values and suits again after shuffle
            valueArray[i] = instance.getCardDeck()[i].getValue();
        }
        for (int i=0; i<52; i++){
            suitArray[i] = instance.getCardDeck()[i].getSuit().name();
        }
        
        assertThat(valueArray, not(equalTo(valueArray2)));              //compare between original deck to test deck to make sure are NOT equal
        assertThat(suitArray, not(equalTo(suitArray2)));
    }
}
