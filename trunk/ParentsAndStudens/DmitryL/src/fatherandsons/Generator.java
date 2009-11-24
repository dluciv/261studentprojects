//Lebedev Dmitry g261 2009 (c)
package fatherandsons;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Random;

public class Generator {

    static Random rnd = new Random();
    static String[] maleNames = {"Иван", "Антон", "Владимир", "Михаил", "Сидор"};
    static String[] femaleNames = {"Анна", "Екатерина", "Мария", "Елена", "Яна"};
    static String[] surnames = {"Иванов", "Петров", "Жуков", "Попов", "Козлов"};
    static String[] faculty = {"Матмех", "Химфак", "Пмпу", "Физфак", "Биофак"};
    static int[] ageCategory1 = {17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    static int[] ageCategory2 = {37, 38, 39, 40, 41, 42, 43, 44, 45, 46};

    public static int getAllMoney(LinkedList<IHuman> humans) {
        int result = 0;
        for (IHuman human : humans) {
            if (human instanceof CoolParent) {
                result += ((CoolParent) human).getMoney();
            }
        }
        return result;
    }

    public static double getAverageOfAllMarks(LinkedList<IHuman> humans) {
        double result = 0;
        double numberOfBotans = 0;
        for (IHuman human : humans) {
            if (human instanceof Botan) {
                result += ((Botan) human).averageMark();
                numberOfBotans++;
            }
        }
        return BigDecimal.valueOf(result / numberOfBotans).
                setScale(2, RoundingMode.UP).doubleValue();
    }

    //Generating random between Parent and CoolParent
    public static Parent parentGenerator() {
        if (rnd.nextBoolean()) {
            return new Parent();
        } else {
            return new CoolParent();
        }
    }

    //Generating random between Student and Botan
    public static Student studentGenerator() {
        if (rnd.nextBoolean()) {
            return new Student();
        } else {
            return new Botan();
        }
    }
    static LinkedList<IHuman> humans = new LinkedList();

    //Generating random number of parents, random number of kids
    public static LinkedList<IHuman> generate() {
        int numberOfParents = rnd.nextInt(10) + 1;

        while (numberOfParents > 0) {
            humans.addLast(parentGenerator());
            LinkedList<Student> kids = new LinkedList();
            int numberOfStudents = rnd.nextInt(3) + 1;
            while (numberOfStudents > 0) {
                kids.addLast(studentGenerator());
                kids.getLast().setFather((Parent) humans.getLast());
                numberOfStudents--;
            }

            ((Parent) humans.getLast()).setKids(kids);

            for (Student kid : kids) {
                humans.addLast(kid);
            }
            numberOfParents--;
        }
        for (IHuman human : humans) {
            if (human instanceof Parent) {
                ((Parent) human).printAll();
            }
        }
        return humans;
    }
    //printing information about them, printing the sum of all money of
    //CoolParents and printing average mark of all Botans
    public static void generateAndPrint() {
        humans = generate();
        System.out.println("Общая сумма денег крутых родителей: " + getAllMoney(humans));
        System.out.println("Средний балл среди всех ботанов: " + getAverageOfAllMarks(humans));
    }
}

