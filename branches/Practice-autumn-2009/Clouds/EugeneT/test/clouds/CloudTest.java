/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

import org.junit.Test;
import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class CloudTest {
    Mockery context = new JUnit4Mockery();
    IMagic magic = new Magic();

    @Test
    public void testCreatePuppy() {
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsShiny, 5, DaylightType.Evening);
        ICloud cloud = new Cloud(magic);

        Creature expResult = Creature.Puppy;
        Creature result = cloud.create(weather);

        assertTrue(expResult == result);
    }

    @Test
    public void testCreateKitten() {
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsShiny, 3, DaylightType.Noon);
        ICloud cloud = new Cloud(magic);

        Creature expResult = Creature.Kitten;
        Creature result = cloud.create(weather);

        assertTrue(expResult == result);
    }

    @Test
    public void testCreateHedgehog() {
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsNotShiny, 9, DaylightType.Day);
        ICloud cloud = new Cloud(magic);

        Creature expResult = Creature.Hedgehog;
        Creature result = cloud.create(weather);

        assertTrue(expResult == result);
    }

    @Test
    public void testCreateBearcub() {
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsShiny, 2, DaylightType.Evening);
        ICloud cloud = new Cloud(magic);

        Creature expResult = Creature.Bearcub;
        Creature result = cloud.create(weather);

        assertTrue(expResult == result);
    }

    @Test
    public void testCreatePiglet() {
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsNotShiny, 3, DaylightType.Day);
        ICloud cloud = new Cloud(magic);

        Creature expResult = Creature.Piglet;
        Creature result = cloud.create(weather);

        assertTrue(expResult == result);
    }

    @Test
    public void testCreateBat() {
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsNotShiny, 10, DaylightType.Night);
        ICloud cloud = new Cloud(magic);

        Creature expResult = Creature.Bat;
        Creature result = cloud.create(weather);

        assertTrue(expResult == result);
    }

    @Test
    public void testCreateBallon() {
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsShiny, 0, DaylightType.Noon);
        ICloud cloud = new Cloud(magic);

        Creature expResult = Creature.Ballon;
        Creature result = cloud.create(weather);

        assertTrue(expResult == result);
    }

    @Test
    public void testCallStork() {
        
        final IMagic magic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsShiny, 0, DaylightType.Noon);

        context.checking(new Expectations() {
			{
                oneOf(magic).callStork();
                oneOf(magic).giveBaby(Creature.Ballon);
            }
        });
        
        cloud.create(weather);
    }

    @Test
    public void testCallDaemon() {

        final IMagic magic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(LuminaryType.IsNotShiny, 10, DaylightType.Night);

        context.checking(new Expectations() {
			{
                oneOf(magic).callDaemon();
                oneOf(magic).giveBaby(Creature.Bat);
            }
        });

        cloud.create(weather);
    }
}