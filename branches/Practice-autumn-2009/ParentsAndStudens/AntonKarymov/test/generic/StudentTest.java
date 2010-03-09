/*Anton Karymov,2009,261gr.
  Tests for generic
 */
package generic;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {
    Student armen = new Student("Армен", "Васильевич", "Кривошеев", Sex.male, 18, "математико-механический");
    BotanHelp vaha = new BotanHelp("Ваха", "Игоревич", "Штольц", Sex.male, 19, "ПМПУ");
    Student[] kids = {armen};
    CoolParent fatherGena = new CoolParent("Геннадий", "Андреевич", "Визер", Sex.male, 46, kids);

    public class BotanHelp extends Student {
        public BotanHelp(String name, String fathername, String surname, Sex sex, int age, String faculty) {
            super(name, fathername, surname, sex, age, faculty);
            examsNumber = 2;
            marks = new int[examsNumber];
            marks[0] = 4;
            marks[1] = 5;
        }
    }
    @Test
    public void testGetAverageBotanMark() {
        double averageMark = 4.5;
        assertEquals(averageMark,vaha.getAverageMark(),0.0);
    }

    @Test
    public void testGetAverageStudentMark() {
        double averageMark = 3.0;
        assertEquals(averageMark, armen.getAverageMark(), 0.0);
    }

    @Test
    public void testCountParentStudentMoney() {
        double sum = 30.0;
        assertEquals(sum, fatherGena.countAllMoney(fatherGena.getKids()), 0.0);
    }

    @Test
    public void testEndOfMalePatronymic() {
        String TESTNAME = "Кирилл";
        String TESTPATRONYMIC = "Кириллович";
        assertEquals(TESTPATRONYMIC, GeneratorHumanInfo.generatePatronymic(Sex.male, TESTNAME));
    }

    @Test
    public void testEndOfFemalePatronymic() {
        String TESTNAME = "Олег";
        String TESTPATRONYMIC = "Олеговна";
        assertEquals(TESTPATRONYMIC, GeneratorHumanInfo.generatePatronymic(Sex.female, TESTNAME));
    }

    @Test
    public void testEndOfFemaleSurname() {
        String TESTFATHERSURNAME = "Мхов";
        String TESTSURNAME = "Мхова";
        assertEquals(TESTSURNAME, GeneratorHumanInfo.generateSurname(Sex.female, TESTFATHERSURNAME) );
    }

    @Test
    public void testEndOfMaleSurname() {
        String TESTFATHERSURNAME = "Попов";
        String TESTSURNAME = "Попов";
        assertEquals(TESTSURNAME, GeneratorHumanInfo.generateSurname(Sex.male, TESTFATHERSURNAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullParentTest() {
        new Parent(null, "Андреевич", "Визер", Sex.male, 46, kids);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullCoolParentTest() {
        new CoolParent(null, "Андреевич", "Визер", Sex.male, 46, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullStudentTest() {
        new Student("Василий", "Андреевич", null, Sex.male, 46, "ПМПУ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullBotanTest() {
        new Botan("Василий", "Андреевич", "Визер", Sex.male, - 46, "ПМПУ");
    }
}
