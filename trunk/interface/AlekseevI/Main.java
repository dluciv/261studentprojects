/* Программа Использования "Строительство"
 *
 * @author Alekseev Ilya
 */
package interfacepkg;

public class Main {

    // Медвед1 будет наделён способностью говорить 'Превед'
    private static Construction building1 = new InTrees();
    // Медвед2 будет наделён способностью говорить 'Кревед'
    private static Construction building2 = new Brick();

    public static void main(String[] args) {
        // Медведи разговаривают!!
        building1.build();
        building2.build();
    }
}
