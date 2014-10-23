/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

import java.net.URL;
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
public class ResourceCacheTest {
    
    public ResourceCacheTest() {
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
     * Test of loadResource method, of class ResourceCache.
     */
    @Test
    public void testLoadResource_String() {
        System.out.println("loadResource");
        String name = "";
        ResourceCache instance = new ResourceCacheImpl();
        Object expResult = null;
        Object result = instance.loadResource(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
    }

    /**
     * Test of getResource method, of class ResourceCache.
     */
    @Test
    public void testGetResource() {
        System.out.println("getResource");
        String name = "";
        ResourceCache instance = new ResourceCacheImpl();
        Object expResult = null;
        Object result = instance.getResource(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of loadResource method, of class ResourceCache.
     */
    @Test
    public void testLoadResource_URL() {
        System.out.println("loadResource");
        URL url = null;
        ResourceCache instance = new ResourceCacheImpl();
        Object expResult = null;
        Object result = instance.loadResource(url);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    public class ResourceCacheImpl extends ResourceCache {

        public Object loadResource(URL url) {
            return null;
        }
    }
    
}
