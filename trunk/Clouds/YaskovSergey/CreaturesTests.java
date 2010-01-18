/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package cloudstests;

import org.junit.Test;
import static org.junit.Assert.*;
import clouds.*;

public class CreaturesTests {
    IMagic magic = new Magic();

    @Test
    public void testCreatePiglet() {
        IWeather weather = new WeatherMock(LuminaryType.ISSHINING, 5, DaylightType.MORNING);
        Cloud cloud = new Cloud(magic);

        assertTrue(cloud.Create(weather).creatureType == CreatureType.PIGLET);
    }

    @Test
    public void testCreateBalloon() {
        IWeather weather = new WeatherMock(LuminaryType.ISSHINING, 4, DaylightType.NOON);
        Cloud cloud = new Cloud(magic);

        assertTrue(cloud.Create(weather).creatureType == CreatureType.BALLOON);
    }

    @Test
    public void testCreateBat() {
        IWeather weather = new WeatherMock(LuminaryType.ISSHINING, 3, DaylightType.DAY);
        Cloud cloud = new Cloud(magic);

        assertTrue(cloud.Create(weather).creatureType == CreatureType.BAT);
    }

    @Test
    public void testCreateHedgehog() {
        IWeather weather = new WeatherMock(LuminaryType.ISSHINING, 7, DaylightType.NIGHT);
        Cloud cloud = new Cloud(magic);

        assertTrue(cloud.Create(weather).creatureType == CreatureType.HEDGEHOG);
    }

    @Test
    public void testCreatePuppy() {
        IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, 0, DaylightType.MORNING);
        Cloud cloud = new Cloud(magic);

        assertTrue(cloud.Create(weather).creatureType == CreatureType.PUPPY);
    }

    @Test
    public void testCreateKitten() {
        IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, 11, DaylightType.NOON);
        Cloud cloud = new Cloud(magic);

        assertTrue(cloud.Create(weather).creatureType == CreatureType.KITTEN);
    }

    @Test
    public void testCreateBearcub() {
        IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, 10, DaylightType.MORNING);
        Cloud cloud = new Cloud(magic);

        assertTrue(cloud.Create(weather).creatureType == CreatureType.BEARCUB);
    }
}
