/*
 * (c) Antonov Kirill 2009
 */

package animals;

/**
 *
 * @author Tiesto
 */
public class Main {

    public static void animalVoices(IAnimal justDog, IAnimal justCat, IAnimal justCow) {
        try {
            System.out.println("Dogs says: " + justDog.getNoise());
            System.out.println("Cats says: " + justCat.getNoise());
            System.out.println("Cows says: " + justCow.getNoise());
        } catch (NullPointerException e) {}
    }

    public static void main(String[] args) {
        Cat justCat = new Cat();
        Dog justDog = new Dog();
        Cow justCow = new Cow();
        animalVoices(justDog, justCat, justCow);
    }
	
}
