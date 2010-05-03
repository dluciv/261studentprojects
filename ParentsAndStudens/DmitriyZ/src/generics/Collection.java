/*
 * Parents and Students
 * Collection
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

import java.util.List;

public class Collection {

    public static int getCoolFathersMoney(List<IHuman> people) {
        if (people == null) {
            throw new IllegalArgumentException("Argument can not be null");
        }

        int money = 0;

        for (IHuman human : people) {
            if (human.getSex() == Sex.MALE && human instanceof CoolParent) {
                money += ((CoolParent) human).getMoney();
            }
        }

        return money;
    }

    public static double getRating(List<IHuman> people) {
        if (people == null) {
            throw new IllegalArgumentException("Argument can not be null");
        }

        double rating = 0;
        int count = 0;

        for (IHuman human : people) {

            if (human instanceof Botan) {
                count++;
                rating += ((Botan) human).averageGrade();
            }
        }

        if (count == 0) {
            throw new ArithmeticException("Devide by zero");
        }

        return rating / count;
    }

    public static void show(List<IHuman> people) {
        if (people == null) {
            throw new IllegalArgumentException("Argument can not be null");
        }

        for (IHuman human : people) {
            System.out.println(human.toString());
        }
    }
}