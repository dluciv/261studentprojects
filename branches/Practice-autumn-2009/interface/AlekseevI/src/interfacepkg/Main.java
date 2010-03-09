/* Программа Использования "Строительство"
 *
 * @author Alekseev Ilya
 */
package interfacepkg;

public class Main {
    public static void building (IConstruction construction){
        if(construction == null){
            throw new IllegalArgumentException("Сооруженрие не может быть нулом");
        }
            
        construction.build();
}

public static void main(String[] args){
     IConstruction buildingOfHouse = new House();
     IConstruction buildingOfBarn = new Barn();

    building(buildingOfHouse);
    building(buildingOfBarn);
    }
}
