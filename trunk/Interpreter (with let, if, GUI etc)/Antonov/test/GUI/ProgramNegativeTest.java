/*
 * Test
 * Antonov Kirill(c), 2010
 */
package GUI;

import name.kirill.ml.gui.Program;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProgramNegativeTest {

    @Test
    public void testSimpleLet() throws Exception {
        String programContent = "let x:int = 5 in x + 99";
        Program instance = new Program(programContent);
        String expResult = "10";
        String result = instance.Interpret();
        assertFalse(expResult.equals(result));

    }

    @Test
    public void testSimpleIf() throws Exception {
        String programContent = "if 4<5 then true else false";
        Program instance = new Program(programContent);
        String expResult = "false";
        String result = instance.Interpret();
        assertFalse(expResult.equals(result));

    }

    @Test
    public void testIfWithEquality() throws Exception {
        String programContent = "if 4=5 then 1 else 0";
        Program instance = new Program(programContent);
        String expResult = "1";
        String result = instance.Interpret();
        assertFalse(expResult.equals(result));

    }

    @Test
    public void testIfWithUnEquality() throws Exception {
        String programContent = "if 4!=5 then 1 else 0";
        Program instance = new Program(programContent);
        String expResult = "0";
        String result = instance.Interpret();
        assertFalse(expResult.equals(result));

    }

    
}
