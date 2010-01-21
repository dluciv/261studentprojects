/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animals;

import org.junit.Test;

public class MainTest {

    @Test
    public void testAnimalVoices_null_null() {
        try {
            IAnimal justDog = null;
            IAnimal justCat = null;
            IAnimal justCow = null;
            Main.animalVoices(justDog, justCat, justCow);
        } catch (NullPointerException e) {}
    }

    @Test
    public void testAnimalVoices_null_Cat() {
        try {
            IAnimal justDog = null;
            IAnimal justCat = new Cat();
            IAnimal justCow = null;
            Main.animalVoices(justDog, justCat, justCow);
        } catch (NullPointerException e) {}
    }

    @Test
    public void testAnimalVoices_Dog_Cat_Cow() {
        try {
            System.out.println("animalVoices");
            IAnimal justDog = new Dog();
            IAnimal justCat = new Cat();
            IAnimal justCow = new Cow();
            Main.animalVoices(justDog, justCat, justCow);
        } catch (NullPointerException e) {}
    }

    @Test
    public void testAnimalVoices_Dog_null() {
        try {
            System.out.println("animalVoices");
            IAnimal justDog = new Dog();
            IAnimal justCat = null;
            IAnimal justCow = null;
            Main.animalVoices(justDog, justCat, justCow);
        } catch (NullPointerException e) {}
    }

    @Test
    public void testAnimalVoices_null_Cow() {
        try {
            IAnimal justDog = null;
            IAnimal justCat = null;
            IAnimal justCow = new Cow();
            Main.animalVoices(justDog, justCat, justCow);
        } catch (NullPointerException e) {}
    }

}