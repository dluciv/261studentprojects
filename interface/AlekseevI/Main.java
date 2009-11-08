/* Программа Использования "Строительство"
 *
 * @author Alekseev Ilya
 */
package interfacepkg;

public class Main {

    // Стоить можно из дерева
    private static Construction building1 = new InTrees();
    // строить можно из кирпичей
    private static Construction building2 = new Brick();

    public static void main(String[] args) {
        // Осторожно, идет стройка
        building1.build();
        building2.build();
    }
}
