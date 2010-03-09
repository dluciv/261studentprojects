/*
 * Parents and Students
 * Main
 * Dmitriy Zabranskiy g261 (c)2009
 */
package generics;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<IHuman> people = Generator.generatePeople();

        System.out.println("Список людей:");
        Collection.show(people);

        System.out.println();
        System.out.println("Количество денег у крутых отцов: " + Collection.getCoolFathersMoney(people));
        System.out.println("Средний балл всех ботанов: " + Collection.getRating(people));
    }
}
