/*
 * SubClass CoolParent
 * Zubrilin Andrey,261 gr (c) 2009
 */

package parentsandstudents;

import java.util.List;

public class CoolParent extends Parent {

    public CoolParent(String surname,String name,String patron,boolean sex,int age,Student[] crowd) {
        super( surname,name, patron, sex, age, crowd);
    }

    //Подсчет денег у родителей;
    public int moneyCounter() {

        int result = 0;
        int count = 0;
        for (int i = 0; i < crowd.length; i++)
            for (int j = 0; j < crowd[i].examsNum; j++) {
                count++;
                result += crowd[i].examMarks(j);
        }
        return (result /= count) * 10;
    }

    //Общий капитал;
    public static int parentMoneyCounter(List<IHuman> people) {
        int result = 0;
        for (IHuman i : people)
            if (i instanceof CoolParent) 
                result += ((CoolParent) i).moneyCounter();
        return result;
    }
}
