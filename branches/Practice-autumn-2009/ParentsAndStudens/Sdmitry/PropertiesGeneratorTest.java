/*
 * Generic
 * soldatov dmitry ©, 2009
 */

import generic.*;
import java.util.Random;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.Assert;

public class PropertiesGeneratorTest {

    private PropertiesGenerator gen = new PropertiesGenerator();
    private Random rnd = new Random();

    @Test
    public void maleFatherNameGeneratorTest() {
        String preFatherName = Names.getMaleNames()[rnd.nextInt(Names.getMaleNames().length)];
        String fatherName = gen.generateFatherName(Gender.Male, preFatherName);
        Assert.assertEquals(fatherName, preFatherName + "ович");
    }

    @Test
    public void femaleFatherNameGeneratorTest() {
        String preFatherName = Names.getMaleNames()[rnd.nextInt(Names.getMaleNames().length)];
        String fatherName = gen.generateFatherName(Gender.Female, preFatherName);
        Assert.assertEquals(fatherName, preFatherName + "овна");
    }
}