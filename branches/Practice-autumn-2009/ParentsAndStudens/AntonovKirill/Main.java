/*(c) Antonova Kirilla 2009
 * Engine=)
 */

package parentsstudents;

import java.util.List;

public class Main {
public static void main(String[] args) {
        List<IHuman> humanList = Generator.generateCollection();

        showHumans(humanList);

        System.out.println("Сумма денег крутых отцов:");
        System.out.println(Collection.calcAllMoney(humanList));
        System.out.println("Средняя оценка ботанов:");
        System.out.println(Collection.
                calcAverageGradesOfAllBotans(humanList));
    }

    private static void showHumans(List<IHuman> humanList) {
        int familyCount = 0;

        for (IHuman human : humanList) {
            if (human instanceof Parents) {
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
                    + human.getFatherName());
            System.out.println(human.getAge() + " years old");
            System.out.println(human.getSex());

            if (human instanceof Student) {
                System.out.println(((Student) human).faculty);
            }
            System.out.println("");
        }
    }

}
