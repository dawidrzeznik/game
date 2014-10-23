/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dawidziu
 */
public class WojnaSwiatowTest {
    
    public WojnaSwiatowTest() {
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
     * Test of setGameEnded method, of class WojnaSwiatow.
     */
  

    /**
     * Test of isGameEnded method, of class WojnaSwiatow.
     */
    @Test
    public void testIsGameEnded() {
        System.out.println("isGameEnded");
        WojnaSwiatow instance = new WojnaSwiatow();
        boolean expResult = false;
        boolean result = instance.isGameEnded();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getSZYBKOSC method, of class WojnaSwiatow.
     */
    @Test
    public void testGetSZYBKOSC() {
        System.out.println("getSZYBKOSC");
        WojnaSwiatow instance = new WojnaSwiatow();
        int expResult = 0;
        int result = instance.getSZYBKOSC();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }


    /**
     * Test of getPlayer method, of class WojnaSwiatow.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        WojnaSwiatow instance = new WojnaSwiatow();
        Player expResult = null;
        Player result = instance.getPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }



    
}
