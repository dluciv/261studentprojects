//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<IHuman> humans = HumanGenerator.humanGenerator();
        for (IHuman human : humans) {
            System.out.println(human);
        }
        System.out.println("TotalMeanMark: " + Counter.meanMarks(humans));
        System.out.println("TotalMoney: " + Counter.money(humans));
    }
}
