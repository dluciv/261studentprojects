/*
 * "Fathers and Children"
 * some generics example
 * (c) Yaskov Sergey, 261; 2009
 */

package fathersandchildren;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<IHuman> humanList = Creator.createCollection();

        showHumans(humanList);
        
        System.out.println("Сумма денег крутых отцов:");
        System.out.println(CollectionCalcs.calcAllMoney(humanList));
        System.out.println("Средняя оценка ботанов:");
        System.out.println(CollectionCalcs.
                calcAverageGradesOfAllBotans(humanList));
    }

    public static void showHumans(List<IHuman> humanList) {
        int familyCount = 0;

        for (IHuman human : humanList) {
            if (human instanceof Parent) {
                familyCount++;
                System.out.print(familyCount);
                if (human instanceof CoolParent) {
                    System.out.println(") ------------COOL------------");
                }
                else {
                    System.out.println(") ----------------------------");
                }
            }
            System.out.println(human.getSurname() + " " + human.getName() + " "
                    + human.getPatronymic());
            System.out.println(human.getAge() + " years old");
            System.out.println(human.getSex());

            if (human instanceof Student) {
                System.out.println(((Student) human).faculty);
            }
            System.out.println("");
        }
    }
}
