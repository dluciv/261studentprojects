//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

import java.util.List;

public class Counter {

    public static float meanMarks(List<IHuman> humans) {
        float summMeanMarks = 0;
        float quantityMeanMarks = 0;
        for (IHuman human : humans) {
            List<Student> children = ((Parent) human).getChildren();
            for (Student child : children) {
                if (child instanceof Botan) {
                    summMeanMarks += child.meanMark();
                    quantityMeanMarks++;
                } else {}
            }
        }
        return summMeanMarks / quantityMeanMarks;
    }

    public static float money(List<IHuman> humans) {
        float money = 0;
        for (IHuman human : humans) {
            if (human instanceof CoolParent) {
                money += ((CoolParent) human).getMoney();
            } else {}
        }
        return money;
    }
}
