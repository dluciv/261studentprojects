package tests;

import ide.ide.Program;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @Test
    public void testSimpleLet() throws Exception {
        System.out.println("Interpret");
        String programContent = "let x = 1 in x + 2";
        Program instance = new Program(programContent);
        String expResult = "3";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testDoubleLet() throws Exception {
        System.out.println("Interpret");
        String programContent = "let x = 3 in let y = 2 in x+y";
        Program instance = new Program(programContent);
        String expResult = "5";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testFun() throws Exception {
        System.out.println("Interpret");
        String programContent = "let f = fun x -> x - 1  in f 10";
        Program instance = new Program(programContent);
        String expResult = "9";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testIfThen1() throws Exception {
        System.out.println("Interpret");
        String programContent = "if 3 > 0 then 1 else 0";
        Program instance = new Program(programContent);
        String expResult = "1";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testIfThen0() throws Exception {
        System.out.println("Interpret");
        String programContent = "if 3 > 6 then 1 else 0";
        Program instance = new Program(programContent);
        String expResult = "0";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testLetSq() throws Exception {
        System.out.println("Interpret");
        String programContent = "let x = 4 in begin 1+3; 5 end";
        Program instance = new Program(programContent);
        String expResult = "5";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testLetSq2() throws Exception {
        System.out.println("Interpret");
        String programContent = "let x = 4 in begin 1+3; 5; 6-3; 90/10 end";
        Program instance = new Program(programContent);
        String expResult = "9";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testAr() throws Exception {
        System.out.println("Interpret");
        String programContent = "begin 185/5*45-67*34+456-7 end";
        Program instance = new Program(programContent);
        String expResult = "-164";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testSqBegin() throws Exception {
        System.out.println("Interpret");
        String programContent = "begin if 3>5 then print(0) else print(1)  end";
        Program instance = new Program(programContent);
        String expResult = "1";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

     @Test
    public void testPrint() throws Exception {
        System.out.println("Interpret");
        String programContent = "print(164)";
        Program instance = new Program(programContent);
        String expResult = "164";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }
}