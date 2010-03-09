/*
 * Tests: 
 * 1. calling demon or stork according to the animal
 * Savenko Maria ©2009
 */

package msavenko.tests;

import msavenko.*;
import msavenko.ICreature.CreatureType;
import msavenko.IDaylight.DaylightType;

import org.junit.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;

public class CollaborationTests {
    static Mockery context = new JUnit4Mockery();
    static IDaylight daylight = context.mock(IDaylight.class);
    static IWind wind = context.mock(IWind.class);
    static ILuminary luminary = context.mock(ILuminary.class);
    static IMagic magic = context.mock(IMagic.class);
    
    class MockFactory implements IWeatherFactory {

        @Override
        public IDaylight CreateDaylight() {
            return daylight;
        }

        @Override
        public ILuminary CreateLuminary() {
            return luminary;
        }

        @Override
        public IWind CreateWind() {
            return wind;
        }

    }

    @Test
    public void SendKitten() {
        IWeatherFactory factory = new MockFactory();
        ICloud cloud = new Cloud(factory);

        context.checking(new Expectations() {
            {
                oneOf(luminary).IsShining();
                will(returnValue(true));
                oneOf(wind).getWindPower();
                will(returnValue(0));
                oneOf(daylight).getDaylight();
                will(returnValue(DaylightType.Morning));
                oneOf(magic).CallStork();
            }
        });

        context.assertIsSatisfied();
    }

}
