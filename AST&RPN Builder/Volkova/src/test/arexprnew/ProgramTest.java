/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexprnew;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ide.Program;

/**
 *
 * @author kate
 */
public class ProgramTest {

    public ProgramTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Interpret method, of class Program.
     */
    @Test
    public void testSimpleLet() throws Exception {
        System.out.println("Interpret");
        String programContent = "let x = 1 in x + 2";
        Program instance = new Program(programContent);
        String expResult = "3";
        String result = instance.Interpret();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDoubleLet() throws Exception {
        System.out.println("Interpret");
        String programContent = "let x = 3 in let y = 2 in x+y";
        Program instance = new Program(programContent);
        String expResult = "5";
        String result = instance.Interpret();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }


    @Test
    public void testFun() throws Exception {
        System.out.println("Interpret");
        String programContent = "let f = fun x -> x - 1  in f 10";
        Program instance = new Program(programContent);
        String expResult = "9";
        String result = instance.Interpret();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIfThen1() throws Exception {
        System.out.println("Interpret");
        String programContent = "if 3 > 0 then 1 else 0";
        Program instance = new Program(programContent);
        String expResult = "1";
        String result = instance.Interpret();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIfThen0() throws Exception {
        System.out.println("Interpret");
        String programContent = "if 3 > 6 then 1 else 0";
        Program instance = new Program(programContent);
        String expResult = "0";
        String result = instance.Interpret();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testIfThenTrue() throws Exception {
        System.out.println("Interpret");
        String programContent = "let x = 4 in begin 1+3; 5 end";
        Program instance = new Program(programContent);
        String expResult = "5";
        String result = instance.Interpret();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}