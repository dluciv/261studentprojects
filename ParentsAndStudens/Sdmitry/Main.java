/*
 * Generic
 * soldatov dmitry ©, 2009
 */
package generic;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        HumanList creator = new HumanList();
        LinkedList humanList = new LinkedList();

        humanList = (LinkedList) creator.createHumanList();
        creator.getNames(humanList);
        System.out.println("Средняя оценка ботанов: " +
                creator.getBotansAverageMark(humanList));
        System.out.println("Общих денег: " + creator.countParentMoney(humanList));
    }
}
