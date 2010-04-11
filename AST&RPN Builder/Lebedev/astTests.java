package astTests;

import ast.Lexer;
import ast.Parser;
import org.junit.Assert;
import org.junit.Test;

public class astTests {

    @Test
    public void Addition() throws Exception {
        Parser parser = new Parser("2/2+2*2");
        Assert.assertTrue(true);
        Assert.assertTrue(2 / 2 + 2 * 2 == parser.expr().evaluate());
    }

    @Test
    public void Subtraction() throws Exception {
        Parser parser = new Parser("2/2-2*2");
        Assert.assertTrue(2 / 2 - 2 * 2 == parser.expr().evaluate());
    }

    @Test
    public void Brackets() throws Exception {
        Parser parser = new Parser("2/(3-2)*2");
        Assert.assertTrue(2 / (3 - 2) * 2 == parser.expr().evaluate());
    }
}
