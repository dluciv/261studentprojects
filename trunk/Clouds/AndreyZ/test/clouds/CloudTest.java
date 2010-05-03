/**
 * MockTests
 * @author Zubrilin Andrey
 * group 261 (c) 2009
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
    public void batTest() {
        IWFactory weather = new WFacMock(LuminaryType.Shiny, 0, DaylightType.Noon);
        ICloud cloud = new Cloud(magic);

        CreatureType result = cloud.callBaby(weather);

        assertTrue(CreatureType.Bat == result);
     }

         @Test
    public void bearTest() {
        IWFactory weather = new WFacMock(LuminaryType.NotShiny, 2, DaylightType.Day);
        ICloud cloud = new Cloud(magic);

        CreatureType result = cloud.callBaby(weather);

        assertTrue(CreatureType.Bear == result);
    }


    @Test
    public void dogTest() {
        IWFactory weather = new WFacMock(LuminaryType.NotShiny, 4, DaylightType.Evening);
        ICloud cloud = new Cloud(magic);

        CreatureType result = cloud.callBaby(weather);

        assertTrue(CreatureType.Dog == result);
    }

    @Test
    public void kittenTest() {
        IWFactory weather = new WFacMock(LuminaryType.Shiny, 6, DaylightType.Night);
        ICloud cloud = new Cloud(magic);

        CreatureType result = cloud.callBaby(weather);

        assertTrue(CreatureType.Kitten == result);
    }

    @Test
    public void pigTest() {
        IWFactory weather = new WFacMock(LuminaryType.NotShiny, 8, DaylightType.Noon);
        ICloud cloud = new Cloud(magic);

        CreatureType result = cloud.callBaby(weather);

        assertTrue(CreatureType.Piglet == result);
    }


    @Test
    public void puppyTest() {
        IWFactory weather = new WFacMock(LuminaryType.Shiny, 10, DaylightType.Day);
        ICloud cloud = new Cloud(magic);

        CreatureType result = cloud.callBaby(weather);

        assertTrue(CreatureType.Puppy == result);
    }   

    @Test
    public void daemonTest() {

        final IMagic magic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(magic);
        IWFactory weather = new WFacMock(LuminaryType.Shiny, 10, DaylightType.Day);
        context.checking(new Expectations() {
			{
                oneOf(magic).callDaemon();
                oneOf(magic).giveBaby(CreatureType.Puppy);
            }
        });

        cloud.callBaby(weather);
    }
    
    @Test
    public void storkTest() {
        
        final IMagic magic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(magic);
        IWFactory weather = new WFacMock(LuminaryType.Shiny, 0, DaylightType.Noon);

        context.checking(new Expectations() {
			{
                oneOf(magic).callStork();
                oneOf(magic).giveBaby(CreatureType.Bat);
            }
        });
        
        cloud.callBaby(weather);
    }


}