/* @author Eugene Todoruk
 * group 261
 */

package parentsAndChildren;

import java.util.List;

public class MyCollection {

    public static int getParentsMoney(List<IHuman> people) {
        if (people == null) {
            throw new IllegalArgumentException("Null Pointer");
        }
        
        int money = 0;

        for (IHuman human : people) {
            if (human instanceof CoolParent) {
                money += ((CoolParent) human).getMoney();
            }
        }

        return money;
    }

    public static double getMeanRating(List<IHuman> people) {
        if (people == null) {
            throw new IllegalArgumentException("Null Pointer");
        }
        
        double rating = 0;
        int botanCount = 0;

        for (IHuman human : people) {

            if (human instanceof Botan) {
                botanCount++;
                rating += ((Botan)human).getMeanRating();
            }
        }
        
        if (botanCount == 0) {
            throw new ArithmeticException("Devide by zero");
        }

        return rating / botanCount;
    }

    public static void show(List<IHuman> people) {
        if (people == null) {
            throw new IllegalArgumentException("Null Pointer");
        }
        
        System.out.println("Всего людей в коллекции: " + people.size());

        for (IHuman human : people) {

            System.out.println("--------------------------------------------");

            System.out.println(human);
        }
    }
}
