/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

import org.hibernate.SessionFactory;
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
public class HibernateUtilTest {
    
    public HibernateUtilTest() {
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
     * Test of getSessionFactory method, of class HibernateUtil.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        //SessionFactory expResult = null;
        SessionFactory result = HibernateUtil.getSessionFactory();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
