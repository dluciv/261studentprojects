/*Program Fathers and childrens
 * Alekseev Ilya(c)
 */
package generic;

import java.util.*;

public class Main {

    public static int getParentMoney(List<IHuman> people) {
        int money = 0;
        for (IHuman human : people) {
            if (human instanceof CoolParent) {
                money += ((CoolParent) human).getMoney();
            }
        }
        return money;
    }

    public static double getMeanMark(List<IHuman> people) {
        double ball = 0;
        int countBotan = 0;

        for (IHuman human : people) {
            if (human instanceof Botan) {
                countBotan++;
                ball += ((Botan) human).getMeanMark();
            }
        }
        return ball / countBotan;
    }

    public static void main(String[] args) {
        List<IHuman> people = Generator.generatePeople();

        for (IHuman human : people) {
            System.out.println("-----------------------------");
            System.out.println(human);

        }
        System.out.println("-----------------------------");
        System.out.println("Колличество Денег у классных родителей : " + getParentMoney(people));
        System.out.println("Средняя оценка ботанов : " +
                getMeanMark(people));
    }
}
