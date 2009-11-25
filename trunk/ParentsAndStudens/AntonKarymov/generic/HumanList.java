package generic;

import java.util.Arrays;
import java.util.Random;
import java.util.LinkedList;

public class HumanList {

    private static Random rnd = new Random();
    private static int minimumParentNumber = 1;
    private static int maximumParentNumber = 5;
    private static LinkedList<IHuman> humanList = new LinkedList<IHuman>();

    public static LinkedList<IHuman> createHumanList() {
        int parentNumber = minimumParentNumber + rnd.nextInt(maximumParentNumber);
        for (int i = 0; i < parentNumber; i++) {
            Parent parent = (Parent) HumanСreater.createParent();
            humanList.add((IHuman) parent);
            for (int j = 0; j < parent.getKidsNumber(); j++) {
                Student student = (Student) HumanСreater.createStudent(parent.getName());
                humanList.add((IHuman) student);
            }
        }
        return humanList;
    }

    public static void printHuman(LinkedList<IHuman> humanList) {
        for (IHuman human : humanList) {
            if (human instanceof Parent) {
                Parent.printParent((Parent) human);
            } else {
                Student.printStudent((Student) human);
            }
        }
    }

    public static void printCoolParentMoney(double sumCoolParentMoney) {
        System.out.printf("%s %s %s", "На данный момент у крутых отцов:", sumCoolParentMoney, "у.е");
        System.out.println();
    }

    public static void printBotanAveregeMarks(double BotanAveregeMarks) {
        System.out.printf("%s %s", "Средняя оценка по ботаникам:", BotanAveregeMarks);
        System.out.println();
    }

    public static double countParentMoney() {
        double sumCoolParentMoney = 0;

        for (IHuman human : humanList) {//проходиться по списку полученому от  криат хуман лист (каждый обьект из списка)
            if (human instanceof CoolParent) {
                sumCoolParentMoney +=((CoolParent) human).countMoney(((CoolParent) human).getKids());
            }
        }
        return sumCoolParentMoney;
    }

    public static double countAveregeBotanMarks() {
        double sumBotanMarks = 0.0;
        int count = 0;
        for (IHuman human : humanList) {//проходиться по списку полученому от  криат хуман лист (каждый обьект из списка)
            if (human instanceof Botan) {
                count += 1;
                sumBotanMarks += (((Student) human).getAveregeMark(((Botan) human).getMarks()));
            }
         }
        return sumBotanMarks/count;
    }
}
