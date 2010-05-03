/*
 * SubClass Botan
 * Zubrilin Andrey,261 gr (c) 2009
 */
package parentsandstudents;

import java.util.Random;
import java.util.List;

public class Botan extends Student {

    int[] marks;
    public Botan(String surname,String name,String patron,boolean sex,int age,String fac){
        super(surname,name, patron, sex, age,fac);
        marks = new int[examsNum];
        Random random = new Random();
        for(int i = 0; i < marks.length; i++)
            marks[i] = 3 + random.nextInt(3);
    }

    //Средний бал оценок у ботанов;
    public static int markCounter(List<IHuman> humans) {
        int result = 0;
        int count = 0;
        for (IHuman i : humans)
            if (i instanceof Botan) {
                Botan botan = (Botan) i;
                count += botan.examsNum;
                for (int j = 0; j < botan.examsNum; j++)
                    result += botan.marks[j];
            }
        return result / count;
    }
}