/*
 * just AnimalVoices method tests
 * soldatov dmitry (c), 09
 */
package interfacepkg;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testAnimalVoices_null_null() {
        IAnimal justDog = null;
        IAnimal justCat = null;
        Main.animalVoices(justDog, justCat);
    }

    @Test
    public void testAnimalVoices_null_Cat() {
        IAnimal justDog = null;
        IAnimal justCat = new Cat();
        Main.animalVoices(justDog, justCat);
    }

    @Test
    public void testAnimalVoices_Dog_Cat() {
        System.out.println("animalVoices");
        IAnimal justDog = new Dog();
        IAnimal justCat = new Cat();
        Main.animalVoices(justDog, justCat);
    }

    @Test
    public void testAnimalVoices_Dog_null() {
        System.out.println("animalVoices");
        IAnimal justDog = new Dog();
        IAnimal justCat = null;
        Main.animalVoices(justDog, justCat);
    }
	
}