/* Program UseInterface
 * @author
 * Eugene Todoruk
 * group 261
 */

public class Main {

    private static IMedved medved1 = new Preved();
    private static IMedved medved2 = new Kreved();

    public static void main(String[] args) {
        medved1.say();
        medved2.say();
    }
}
