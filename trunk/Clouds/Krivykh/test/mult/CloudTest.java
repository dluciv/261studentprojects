//(с) Кривых Алексей 2009г.
//Cloud
package mult;

import org.junit.Test;
import static org.junit.Assert.*;

public class CloudTest {

    @Test
    public void testCreateBAT() {
        Magic magic = new Magic();
        Cloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(EnumWind.LITTLE,
                EnumDaylight.MORNING, EnumLuminary.ISSHINING);
        assertEquals(CreatureType.BAT, cloud.create(weather).getType());
    }

    @Test
    public void testCreateHEDGEHOG() {
        Magic magic = new Magic();
        Cloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(EnumWind.MIDDLE,
                EnumDaylight.NOON, EnumLuminary.ISSHINING);
        assertEquals(CreatureType.HEDGEHOG, cloud.create(weather).getType());
    }

    @Test
    public void testCreatePIGLET() {
        Magic magic = new Magic();
        Cloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(EnumWind.STRONG,
                EnumDaylight.EVNING, EnumLuminary.ISSHINING);
        assertEquals(CreatureType.PIGLET, cloud.create(weather).getType());
    }

    @Test
    public void testCreateBEARCUB() {
        Magic magic = new Magic();
        Cloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(EnumWind.LITTLE,
                EnumDaylight.MORNING, EnumLuminary.NOTSHINING);
        assertEquals(CreatureType.BEARCUB, cloud.create(weather).getType());
    }

    @Test
    public void testCreateKITTEN() {
        Magic magic = new Magic();
        Cloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(EnumWind.MIDDLE,
                EnumDaylight.NOON, EnumLuminary.NOTSHINING);
        assertEquals(CreatureType.KITTEN, cloud.create(weather).getType());
    }

    @Test
    public void testCreatePUPPY() {
        Magic magic = new Magic();
        Cloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(EnumWind.STRONG,
                EnumDaylight.EVNING, EnumLuminary.NOTSHINING);
        assertEquals(CreatureType.PUPPY, cloud.create(weather).getType());
    }

    @Test
    public void testCreateBALLOON() {
        Magic magic = new Magic();
        Cloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(EnumWind.VERYSTRONG,
                EnumDaylight.EVNING, EnumLuminary.NOTSHINING);
        assertEquals(CreatureType.BALLOON, cloud.create(weather).getType());
    }
}