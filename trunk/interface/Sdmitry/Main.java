/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacepkg;

/**
 *
 * @author Dmitry
 */
public class Main {

    public static void main(String[] args) {
        Cat justCat = new Cat();
        Dog justDog = new Dog();

        System.out.println("Cats says: " + justCat.getNoise());
        System.out.println("Dogs says: " + justDog.getNoise());
    }
}
