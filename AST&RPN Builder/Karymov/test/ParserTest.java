

import org.junit.Assert;
import org.junit.Test;
import interpretator.Lexer;
import interpretator.Parser;
import static org.junit.Assert.*;

public class ParserTest {
     Lexer lexerTest = new Lexer("1-2-3");
     Parser parserTest = new Parser(lexerTest);
    @Test
    public void testGetAverageBotanMark() {
        Assert.assertEquals("1 2 - 3 -", parserTest.Calculate().toString());
    }
}
