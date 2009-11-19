/*
 * Реализация интерфейса Животного посредством Кошки, которая как бы говорить нам "мяу!"
 * soldatov dmitry (c), 09
 */
 
package interfacepkg;

public class Cat implements IAnimal {

    public String getNoise() {
        return "meow!";
    }

}
