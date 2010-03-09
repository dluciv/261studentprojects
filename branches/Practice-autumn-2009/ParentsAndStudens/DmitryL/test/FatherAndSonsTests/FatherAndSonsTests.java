//Lebedev Dmitry g261 2009 (c)
package FatherAndSonsTests;

import fatherandsons.Student;
import fatherandsons.Botan;
import fatherandsons.Generator;
import fatherandsons.IHuman;
import fatherandsons.Parent;
import fatherandsons.Sex;
import org.junit.*;
import org.junit.Assert;

public class FatherAndSonsTests {

    final int THE_LOWEST_MARK = 2;
    final Sex SEX = Sex.female;
    final String PATRONYMIC_END = "овна";
    final String SURNAME_END = "а";
    final IHuman HUMAN_ONE = Generator.parentGenerator();
    final IHuman HUMAN_TWO = Generator.studentGenerator();

    @Test
    public void markOfBotanTest() {
        Assert.assertTrue(Botan.mark() > THE_LOWEST_MARK);
    }

    @Test
    public void generateMarkTest() {
        Assert.assertNotNull(Botan.generateMarks());
    }

    @Test
    public void patronymicEndTest() {
        Assert.assertEquals(PATRONYMIC_END, Student.patronymicEnd(SEX));
    }

    @Test
    public void surnameEndTest() {
        Assert.assertEquals(SURNAME_END, Student.surnameEnd(SEX));
    }

    @Test
    public void parentGeneratorTest() {
        Assert.assertTrue(HUMAN_ONE instanceof Parent);
    }

    @Test
    public void studentGeneratorTest() {
        Assert.assertTrue(HUMAN_TWO instanceof Student);
    }

    @Test
    public void getAllMoneyTest() {
        Assert.assertTrue(Generator.getAllMoney(Generator.generate()) > 0);
    }

    @Test
    public void getAverageOfAllMarksTest() {
        Assert.assertTrue(Generator.getAverageOfAllMarks(Generator.generate()) > 0);
    }
}

