/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

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
public class ConnectTest {
    
    public ConnectTest() {
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
     * Test of odczyt method, of class Connect.
     */
    @Test
    public void testOdczyt() {
        System.out.println("odczyt");
        Connect instance = new Connect();
        instance.odczyt();
        // TODO review the generated test code and remove the default call to fail.
        assertNotNull(instance);
    }
    
}
