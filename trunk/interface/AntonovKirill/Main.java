/*(C)Antonova Kirilla 2009
 * Example of Interface on Pet
 */

package interfacepkg;

interface Voice{
    void voice();
}

class Dog implements Voice{
    public void voice(){
        System.out.println("Gav-Gav!");
    }
}

class Cat implements Voice{
    public void voice(){
        System.out.println("Miaou!");
    }
}

class Cow implements Voice{
    public void voice(){
        System.out.println("Mu-u-u!");
    }

}
public class Main {
    public static void main(String[] args) {
            Voice[] singer = new Voice[3];
            singer[1] = new Dog();
            singer[2] = new Cat();
            singer[3] = new Cow();
            for(int i = 0; i<singer.length; i++)
            singer[i].voice();
        }
}
