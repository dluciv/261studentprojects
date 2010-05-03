/*
 * just interface
 * soldatov dmitry (c), 09
 */
package interfacepkg;

public class Main {

    public static void animalVoices(IAnimal justDog, IAnimal justCat) {
        try {
            System.out.println("Dogs says: " + justDog.getNoise());
            System.out.println("Cats says: " + justCat.getNoise());
        } catch (NullPointerException e) {}
    }

    public static void main(String[] args) {
        Cat justCat = new Cat();
        Dog justDog = new Dog();

        animalVoices(justDog, justCat);
    }
	
}
