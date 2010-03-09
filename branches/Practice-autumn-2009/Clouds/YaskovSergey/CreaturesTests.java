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
    int i;

    final int MIN_WIND_ONE = 0;
    final int MAX_WIND_ONE = 5;

    final int MIN_WIND_TWO = 6;
    final int MAX_WIND_TWO = 11;

    @Test
    public void testCreatePiglet() {
        Cloud cloud = new Cloud(magic);

        for (i = MIN_WIND_ONE; i <= MAX_WIND_ONE; i++) {
            IWeather weatherOne = new WeatherMock(LuminaryType.ISSHINING, i, DaylightType.MORNING);
            IWeather weatherTwo = new WeatherMock(LuminaryType.ISNOTSHINING, i, DaylightType.NIGHT);

            assertTrue(cloud.Create(weatherOne).creatureType == CreatureType.PIGLET);
            assertTrue(cloud.Create(weatherTwo).creatureType == CreatureType.PIGLET);
        }
    }

    @Test
    public void testCreateBalloon() {
        Cloud cloud = new Cloud(magic);

        for (i = MIN_WIND_ONE; i <= MAX_WIND_ONE; i++) {
            IWeather weather = new WeatherMock(LuminaryType.ISSHINING, i, DaylightType.NOON);
            assertTrue(cloud.Create(weather).creatureType == CreatureType.BALLOON);
        }
        for (i = MIN_WIND_TWO; i <= MAX_WIND_TWO; i++) {
            IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, i, DaylightType.NIGHT);
            assertTrue(cloud.Create(weather).creatureType == CreatureType.BALLOON);
        }
    }

    @Test
    public void testCreateBat() {
        Cloud cloud = new Cloud(magic);

        for (i = MIN_WIND_ONE; i <= MAX_WIND_ONE; i++) {
            IWeather weatherOne = new WeatherMock(LuminaryType.ISSHINING, i, DaylightType.DAY);
            IWeather weatherTwo = new WeatherMock(LuminaryType.ISNOTSHINING, i, DaylightType.NOON);

            assertTrue(cloud.Create(weatherOne).creatureType == CreatureType.BAT);
            assertTrue(cloud.Create(weatherTwo).creatureType == CreatureType.BAT);
        }
        for (i = MIN_WIND_TWO; i <= MAX_WIND_TWO; i++) {
            IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, i, DaylightType.DAY);
            assertTrue(cloud.Create(weather).creatureType == CreatureType.BAT);
        }
    }

    @Test
    public void testCreateHedgehog() {
        Cloud cloud = new Cloud(magic);

        for (i = MIN_WIND_ONE; i <= MAX_WIND_ONE; i++) {
            IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, i, DaylightType.DAY);
            assertTrue(cloud.Create(weather).creatureType == CreatureType.HEDGEHOG);
        }
        for (i = MIN_WIND_TWO; i <= MAX_WIND_TWO; i++) {
            IWeather weather = new WeatherMock(LuminaryType.ISSHINING, i, DaylightType.NIGHT);
            assertTrue(cloud.Create(weather).creatureType == CreatureType.HEDGEHOG);
        }
    }

    @Test
    public void testCreatePuppy() {
        Cloud cloud = new Cloud(magic);

        for (i = MAX_WIND_TWO; i <= MAX_WIND_TWO; i++) {
            IWeather weatherOne = new WeatherMock(LuminaryType.ISSHINING, i, DaylightType.NOON);
            IWeather weatherTwo = new WeatherMock(LuminaryType.ISSHINING, i, DaylightType.DAY);

            assertTrue(cloud.Create(weatherOne).creatureType == CreatureType.PUPPY);
            assertTrue(cloud.Create(weatherTwo).creatureType == CreatureType.PUPPY);
        }
        for (i = MIN_WIND_ONE; i <= MAX_WIND_ONE; i++) {
            IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, i, DaylightType.MORNING);
            assertTrue(cloud.Create(weather).creatureType == CreatureType.PUPPY);
        }
    }

    @Test
    public void testCreateKitten() {
        Cloud cloud = new Cloud(magic);

        for (i = MIN_WIND_TWO; i <= MAX_WIND_TWO; i++) {
            IWeather weatherOne = new WeatherMock(LuminaryType.ISSHINING, i, DaylightType.MORNING);
            IWeather weatherTwo = new WeatherMock(LuminaryType.ISNOTSHINING, i, DaylightType.NOON);

            assertTrue(cloud.Create(weatherOne).creatureType == CreatureType.KITTEN);
            assertTrue(cloud.Create(weatherTwo).creatureType == CreatureType.KITTEN);
        }
    }

    @Test
    public void testCreateBearcub() {
        Cloud cloud = new Cloud(magic);
        
        for (i = MIN_WIND_ONE; i <= MAX_WIND_ONE; i++) {
            IWeather weather = new WeatherMock(LuminaryType.ISSHINING, i, DaylightType.NIGHT);
            assertTrue(cloud.Create(weather).creatureType == CreatureType.BEARCUB);
        }
        for (i = MIN_WIND_TWO; i <= MAX_WIND_TWO; i++) {
            IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, i, DaylightType.MORNING);
            assertTrue(cloud.Create(weather).creatureType == CreatureType.BEARCUB);
        }
    }
}
