/*
 * Parents And Studets , Main ;
 * Zubrilin Andrey,261 gr (c) 2009
 */
package parentsandstudents;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        LinkedList<IHuman> list = PeopleGeneration.generateCollection();

        //Вывод списка;
        System.out.println("Список людей:");

        for (IHuman person : list)
            PeopleGeneration.info(person);

        System.out.print("Средняя балл у ботанов:");
        System.out.println(Botan.markCounter(list));

        System.out.print("Количество денег у крутых родителей:");
        System.out.println(CoolParent.parentMoneyCounter(list));
    }

}
