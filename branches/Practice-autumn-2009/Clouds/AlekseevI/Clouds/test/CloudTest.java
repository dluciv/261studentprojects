/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clouds;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lii
 */
public class CloudTest {

    @Test
    public void testCreatePappy() {

        IFuctoryWeather fuctoryWeather = new MockFuctoryWheather(EnumDaylight.MORNING, EnumLuminary.ISSHINY, EnumWinds.STORM);
        IMagic magic = new Magic();
        ICloud cloud = new Cloud(magic);
        assertEquals(cloud.create(fuctoryWeather).getType(), CreatureType.PUPPY);
    }

    @Test
    public void testCreatePiglet() {

        IFuctoryWeather fuctoryWeather = new MockFuctoryWheather(EnumDaylight.MORNING, EnumLuminary.CLOUDY, EnumWinds.STORM);
        IMagic magic = new Magic();
        ICloud cloud = new Cloud(magic);
        assertEquals(cloud.create(fuctoryWeather).getType(), CreatureType.PIGLET);
    }

    @Test
    public void testCreateKitten() {

        IFuctoryWeather fuctoryWeather = new MockFuctoryWheather(EnumDaylight.NOON, EnumLuminary.CLOUDY, EnumWinds.STORM);
        IMagic magic = new Magic();
        ICloud cloud = new Cloud(magic);
        assertEquals(cloud.create(fuctoryWeather).getType(), CreatureType.KITTEN);
    }

    @Test
    public void testCreateHedgehog() {

        IFuctoryWeather fuctoryWeather = new MockFuctoryWheather(EnumDaylight.EVENING, EnumLuminary.CLOUDY, EnumWinds.TWISTER);
        IMagic magic = new Magic();
        ICloud cloud = new Cloud(magic);
        assertEquals(cloud.create(fuctoryWeather).getType(), CreatureType.HEDGEHOG);
    }

    @Test
    public void testCreateBearcup() {

        IFuctoryWeather fuctoryWeather = new MockFuctoryWheather(EnumDaylight.NIGHT, EnumLuminary.ISSHINY, EnumWinds.BREEZE);
        IMagic magic = new Magic();
        ICloud cloud = new Cloud(magic);
        assertEquals(cloud.create(fuctoryWeather).getType(), CreatureType.BEARCUB);
    }

    @Test
    public void testCreateBat() {

        IFuctoryWeather fuctoryWeather = new MockFuctoryWheather(EnumDaylight.NOON, EnumLuminary.CLOUDY, EnumWinds.TWISTER);
        IMagic magic = new Magic();
        ICloud cloud = new Cloud(magic);
        assertEquals(cloud.create(fuctoryWeather).getType(), CreatureType.BAT);
    }

    @Test
    public void testCreateBalloon() {

        IFuctoryWeather fuctoryWeather = new MockFuctoryWheather(EnumDaylight.EVENING, EnumLuminary.ISSHINY, EnumWinds.BREEZE);
        IMagic magic = new Magic();
        ICloud cloud = new Cloud(magic);
        assertEquals(cloud.create(fuctoryWeather).getType(), CreatureType.BALLOON);
    }
}
