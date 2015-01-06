package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class PlayerTest {

    public PlayerTest() {
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
     * Test of setPassword method, of class Player.
     */
    @Test
    public void testSetPassword() {
        System.out.println("Testing setPassword() Method...");
        String password = "aba123bab";
        Player instance = new Player("elad", password);
        instance.setPassword(password);
        String expResult = "aba123bab";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoginDate method, of class Player.
     */
    @Test
    public void testSetLoginDate() {
        System.out.println("Testing setLoginDate() Method...");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date loginDate = Calendar.getInstance().getTime();
        Player instance = new Player("elad", "aba123bab");
        instance.setLoginDate(loginDate);
        String expResult = df.format(loginDate);
        String result = df.format(instance.getLoginDate());
        assertEquals(expResult, result);
    }

    /**
     * Test of setBank method, of class Player.
     */
    /*
     @Test
     public void testSetBank() {
     System.out.println("Testing setBank() Method...");
     int bank = 16464871;
     Player instance = new Player("elad", "aba123bab");
     instance.setBank(bank);
     int expResult = 16464871;
     int result = instance.getBank();
     assertEquals(expResult, result);
     }*/
    /**
     * Test of setCurrentHand method, of class Player.
     */
    @Test
    public void testSetCurrentHand() {
        System.out.println("Testing setCurrentHand() Method...");
        Hand currentHand = new Hand();
        Player instance = new Player("elad", "aba123bab");
        instance.setCurrentHand(currentHand);
        Hand expResult = currentHand;
        Hand result = instance.getCurrentHand();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("Testing getName() Method...");
        String name = "elad";
        Player instance = new Player(name, "aba123bab");
        String expResult = "elad";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Player.
     */
    @Test
    public void testGetPassword() {
        System.out.println("Testing getPassword() Method...");
        Player instance = new Player("elad", "aba123bab");
        String expResult = "aba123bab";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoginDate method, of class Player.
     */
    @Test
    public void testGetLoginDate() {
        System.out.println("Testing getLoginDate() Method...");
        Date loginDate = new Date();
        Player instance = new Player("elad", "aba123bab");
        instance.setLoginDate(loginDate);
        Date expResult = loginDate;
        Date result = instance.getLoginDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBank method, of class Player.
     */
    /*
     @Test
     public void testGetBank() {
     System.out.println("Testing getBank() Method...");
     Player instance = new Player("elad", "aba123bab");
     instance.setBank(6544644);
     int expResult = 6544644;
     int result = instance.getBank();
     assertEquals(expResult, result);
     }*/
    /**
     * Test of getCurrentHand method, of class Player.
     */
    @Test
    public void testGetCurrentHand() {
        System.out.println("Testing getCurrentHand() Method...");
        Player instance = new Player("elad", "aba123bab");
        Hand testHand = new Hand();
        instance.setCurrentHand(testHand);
        Hand expResult = testHand;
        Hand result = instance.getCurrentHand();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Player.
     */
    @Test
    public void testEquals() {
        System.out.println("Testing equals() Method...");
        Object instance1 = new Player("elad", "aba123bab");
        Object instance2 = new Hand();
        boolean expResult1 = true;
        boolean result1 = instance1.equals(instance1);
        boolean expResult2 = false;
        boolean result2 = instance1.equals(instance2);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of hashCode method, of class Player.
     */
    @Test
    public void testHashCode() {
        System.out.println("Testing hashCode() Method...");
        Player instance = new Player("elad", "aba123bab");
        int testHash = "elad".hashCode();
        int expResult = 31 * 7 + testHash;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Player.
     */
    /*
     @Test
     public void testToString() {
     System.out.println("Testing toString() Method...");
     Player instance = new Player("elad", "aba123bab");
     Date testDate = new Date();
     int testBank = 12312321;
     instance.setLoginDate(testDate);
     instance.setBank(testBank);
     String expResult = "Player: " + "elad" + " Last seen: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(testDate) + " with " + testBank + " in the bank.";
     String result = instance.toString();
     assertEquals(expResult, result);
     }*/
}
