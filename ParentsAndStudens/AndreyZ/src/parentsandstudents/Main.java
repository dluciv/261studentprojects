/*
 * Parents And Studets , Main ;
 * Zubrilin Andrey,261 gr (c) 2009
 */
package parentsandstudents;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<IHuman> list = PeopleGeneration.generateCollection();

        //Вывод списка;
        System.out.println("Список людей:");

        for (IHuman person : list)
            if(person instanceof CoolParent)
                System.out.println("Отец (Cool) : "+person.Surname() + " " +
                        person.Name() + " " + person.Patron() + "; " + "Возраст : " + person.Age());
            else
                if(person instanceof Parent)
                    System.out.println("Отец        : "+person.Surname() + " " +
                            person.Name() + " " + person.Patron() + "; " + "Возраст : " + person.Age());
                else
                    if(person instanceof Botan)
                        System.out.println("  Студент(ка) (Ботан) : "+person.Surname() + " " +
                                person.Name() + " " + person.Patron() + "; " + "Возраст : " + person.Age());
                    else
                        System.out.println("  Студент(ка)         : "+person.Surname() + " " +
                                person.Name() + " " + person.Patron() + "; " + "Возраст : " + person.Age());

        System.out.print("Количество денег у крутых родителей:");
        System.out.println(CoolParent.parentMoneyCounter(list));

        System.out.print("Средняя оценка у ботанов:");
        System.out.println(Botan.markCounter(list));
    }

}
