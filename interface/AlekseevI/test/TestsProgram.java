
/**Тесты
 *
 * @author Алексеев Илья (с)
 */
import interfacepkg.Barn;
import interfacepkg.House;
import interfacepkg.IConstruction;
import interfacepkg.Main;
import org.junit.Test;

public class TestsProgram {

    @Test(expected = IllegalArgumentException.class)
    public void testNullException() {
        Main.building(null);
        
    }

    @Test
    public void testConstructionOfHouse() {

        IConstruction buildingOfHouse = new House();
        Main.building(buildingOfHouse);
    }

    @Test
    public void testConstructionOfBarn() {

        IConstruction buildingOfBarn = new Barn();
        Main.building(buildingOfBarn);
    }
}

