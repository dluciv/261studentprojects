/* Program UseInterface
 * @author
 * Eugene Todoruk
 * group 261
 */

public class Main {

    public static void prevedFromMedved(IMedved medved) {

        if (medved == null) {
            throw new IllegalArgumentException("IMedved can not be null");
        }
        
        medved.sayPreved();
    }

    public static void main(String[] args) {

        IMedved russianMedved = new RussianMedved();
        IMedved americanMedved = new AmericanMedved();

        prevedFromMedved(russianMedved);
        prevedFromMedved(americanMedved);
    }
}
