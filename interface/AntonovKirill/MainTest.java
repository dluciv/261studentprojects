/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animals;

import org.junit.Test;

public class MainTest {

    @Test
    public void testAnimalVoices_null_null() {
        IAnimal justDog = null;
        IAnimal justCat = null;
        IAnimal justCow = null;
        Main.animalVoices(justDog, justCat, justCow);
    }

    @Test
    public void testAnimalVoices_null_Cat() {
        IAnimal justDog = null;
        IAnimal justCat = new Cat();
        IAnimal justCow = null;
        Main.animalVoices(justDog, justCat, justCow);
    }

    @Test
    public void testAnimalVoices_Dog_Cat_Cow() {
        System.out.println("animalVoices");
        IAnimal justDog = new Dog();
        IAnimal justCat = new Cat();
        IAnimal justCow = new Cow();
        Main.animalVoices(justDog, justCat, justCow);
    }

    @Test
    public void testAnimalVoices_Dog_null() {
        System.out.println("animalVoices");
        IAnimal justDog = new Dog();
        IAnimal justCat = null;
        IAnimal justCow = null;
        Main.animalVoices(justDog, justCat, justCow);
    }

    @Test
    public void testAnimalVoices_null_Cow() {
        IAnimal justDog = null;
        IAnimal justCat = null;
        IAnimal justCow = new Cow();
        Main.animalVoices(justDog, justCat, justCow);
    }

}