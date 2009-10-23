/* Program UseInterface
 * @author
 * Eugene Todoruk
 * group 261
 */

public class Main {

    // Медвед1 будет наделён способностью говорить 'Превед'
    private static IMedved medved1 = new Preved(); 
    // Медвед2 будет наделён способностью говорить 'Кревед'
    private static IMedved medved2 = new Kreved();

    public static void main(String[] args) {
        // Медведи разговаривают!!
        medved1.say();
        medved2.say();
    }
}
