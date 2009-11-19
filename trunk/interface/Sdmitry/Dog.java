/*
 * Реализация интерфейса Животного посредством Собаки, которая как бы говорить нам "гав-гав!"
 * soldatov dmitry (c), 09
 */

package interfacepkg;

public class Dog implements IAnimal {

    public String getNoise() {
        return "wow-wow!";
    }

}
