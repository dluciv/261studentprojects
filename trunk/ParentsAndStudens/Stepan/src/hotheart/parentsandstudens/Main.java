/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.parentsandstudens;

import java.util.List;

/**
 *
 * @author HotHeart
 */
public class Main {

    /**
     * @param args the command line arguments
     */
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
