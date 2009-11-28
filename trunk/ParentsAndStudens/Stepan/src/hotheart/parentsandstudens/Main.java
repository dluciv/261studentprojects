/*
 * Main.
 * Korshakov Stepan - 261 Group - (c) 2009
 */
package hotheart.parentsandstudens;

import java.util.List;

/**
  * @author Korshakov Stepan
 */
public class Main {

    public static void main(String[] args) {

        List<IHuman> list = Generator.generateCollection();

        System.out.println("Список людей:");

        for (IHuman i : list) {
            System.out.println(i.toString());
        }

        System.out.print("Количество денег у крутых родителей:");
        System.out.println(Processor.calcMoney(list));

        System.out.print("Средняя оценка у ботанов:");
        System.out.println(Processor.calcMark(list));

    }
}
