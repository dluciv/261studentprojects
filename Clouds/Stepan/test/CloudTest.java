/*
 * Tests for Cloud allpication by Korshakov Stepan
 * 261 Group - 2009
 */

import hotheart.clouds.Cloud;
import hotheart.clouds.CreatureType;
import hotheart.clouds.DayLightType;
import hotheart.clouds.IDayLight;
import hotheart.clouds.ILuminary;
import hotheart.clouds.IWeather;
import hotheart.clouds.IWind;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Korshakov Stepan
 */
public class CloudTest {

    Mockery context;

    private Cloud createCloud(final boolean isLum, final int wind, final DayLightType daylight) {
        context = new Mockery();
        final IWeather mockWeather = context.mock(IWeather.class);
        final ILuminary mockLum = context.mock(ILuminary.class);
        final IWind mockWind = context.mock(IWind.class);
        final IDayLight mockDayLight = context.mock(IDayLight.class);

        context.checking(new Expectations() {

            {
                allowing(mockWeather).getDayLight();
                will(returnValue(mockDayLight));
                allowing(mockWeather).getLuminary();
                will(returnValue(mockLum));
                allowing(mockWeather).getWind();
                will(returnValue(mockWind));
            }
        });


        context.checking(new Expectations() {

            {
                allowing(mockLum).isShining();
                will(returnValue(isLum));
                allowing(mockWind).getSpeed();
                will(returnValue(wind));
                allowing(mockDayLight).getDayLightType();
                will(returnValue(daylight));
            }
        });

        return new Cloud(mockWeather);
    }

    @Test
    public void testPuppy() {
        Cloud cloud;

        cloud = createCloud(true, 10, DayLightType.NOON);
        assertEquals(cloud.getCreature().getCreatureType(), CreatureType.Puppy);
    }

    @Test
    public void testKitten() {
        Cloud cloud;
        for (int i = 8; i <= 9; i++) {
            cloud = createCloud(true, i, DayLightType.EVENING);
            assertEquals(cloud.getCreature().getCreatureType(), CreatureType.Kitten);
        }
    }

    @Test
    public void testHedgehog() {
        Cloud cloud;
        for (int i = 1; i <= 3; i++) {
            cloud = createCloud(true, i, DayLightType.NIGHT);
            assertEquals(cloud.getCreature().getCreatureType(), CreatureType.Hedgehog);
        }
    }

    @Test
    public void testBearcub() {
        Cloud cloud;
        for (int i = 1; i <= 3; i++) {
            cloud = createCloud(false, i, DayLightType.NIGHT);
            assertEquals(cloud.getCreature().getCreatureType(), CreatureType.Bearcub);
        }
    }

    @Test
    public void testPiglet() {
        Cloud cloud;
        for (int i = 4; i <= 7; i++) {
            cloud = createCloud(true, i, DayLightType.EVENING);
            assertEquals(cloud.getCreature().getCreatureType(), CreatureType.Piglet);
        }
    }

    @Test
    public void testBat() {
        Cloud cloud;
        cloud = createCloud(false, 0, DayLightType.NIGHT);
        assertEquals(cloud.getCreature().getCreatureType(), CreatureType.Bat);
    }

    @Test
    public void testBaloon() {
        Cloud cloud;
        cloud = createCloud(true, 5, DayLightType.MORNING);
        assertEquals(cloud.getCreature().getCreatureType(), CreatureType.Balloon);
    }

    @Test
    public void testNone() {
        Cloud cloud;
        
        cloud = createCloud(true, 5, DayLightType.NIGHT);
        assertEquals(cloud.getCreature(), null);
        
        cloud = createCloud(true, 10, DayLightType.NIGHT);
        assertEquals(cloud.getCreature(), null);
        
        cloud = createCloud(false, 4, DayLightType.EVENING);
        assertEquals(cloud.getCreature(), null);
    }
}