/*
 * SubClass CoolParent
 * Zubrilin Andrey,261 gr (c) 2009
 */

package parentsandstudents;

import java.util.*;

public class CoolParent extends Parent {

    public int money;
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
        money = result;
        return (result /= count) * 10;
    }

    //Общий капитал;
    public static int parentMoneyCounter(LinkedList<IHuman> crowd) {
        if (crowd == null) {
            throw new IllegalArgumentException("Null");
        }
        int result = 0;
        for (IHuman i : crowd)
            if (i instanceof CoolParent) 
                result += ((CoolParent) i).moneyCounter();
        return result;
    }
    @Override
    public HumanType hType(){
        return HumanType.CoolParent;
    }

    @Override
    public String getInfo(){
        String info ;
        info = "Отец (Cool)       : "+surname + " " +
                    name + " " + patron + "; " + "Возраст : " + age;
        return info;
    }
}
