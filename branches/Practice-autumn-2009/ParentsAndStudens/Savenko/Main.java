/*
 * prints money amount and general mean mark
 * Savenko Maria (c)2009
 */
package msavenko.parentsandstudens;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<IHuman> list = Generator.generateCollection();

        System.out.println("Содержание списка:");

        for (IHuman i : list) {
            System.out.println(i.toString());
        }

        System.out.print("Колличество денег у отцов:");
        System.out.println(Generator.calcMoney(list));

        System.out.print("Средний балл ботанов:");
        System.out.println(Generator.calcMark(list));

    }
}
