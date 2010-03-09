/*
 * Generic
 * soldatov dmitry ©, 2009
 */
package generic;

import java.util.*;

public class HumanList<IHuman> {

    private HumanCreator creator = new HumanCreator();
    private Random rnd = new Random();
    private int parentNumber = 1 + rnd.nextInt(5);

    public List<IHuman> createHumanList() {

        LinkedList humanList = new LinkedList();

        for (int i = 0; i < parentNumber; i++) {
            Parent parent = creator.createParent();
            humanList.add(parent);

            for (int j = 0; j < ((Parent) parent).getKidsNumber(); j++) {
                humanList.add(((Parent) parent).getKids()[j]);
            }
        }
        return humanList;
    }

    public void getNames(LinkedList<IHuman> humanList) {

        for (IHuman human : humanList) {
            if (human instanceof Parent) {
                System.out.println(((Parent) human).getName() + " " +
                        ((Parent) human).getFatherName() + " " +
                        ((Parent) human).getLastName() + " " +
                        ((Parent) human).getAge());
            } else {
                System.out.println(((Student) human).getName() + " " +
                        ((Student) human).getFatherName() + " " +
                        ((Student) human).getLastName() + " " +
                        ((Student) human).getAge() + ", " +
                        ((Student) human).getAverageMark() + " " +
                        ((Student) human).getFaculty());
            }
        }
    }

    public float getBotansAverageMark(LinkedList<IHuman> humanList) {

        float allSum = 0;
        int count = 0;
        for (IHuman human : humanList) {
            if (human instanceof Botan) {
                count++;
                allSum += ((Botan) human).getAverageMark();
            }
        }
        return allSum / (float) count;
    }

    public float countParentMoney(LinkedList<IHuman> humanList) {
        float allMoney = 0;
        for (IHuman human : humanList) {
            if (human instanceof CoolParent) {
                allMoney += ((CoolParent) human).countMoney(((CoolParent) human).getKids());
            }
        }
        return allMoney;
    }
}
