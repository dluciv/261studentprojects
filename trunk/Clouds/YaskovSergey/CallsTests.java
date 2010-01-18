/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package cloudstests;

import org.junit.Test;

import clouds.*;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class CallsTests {
    Mockery context = new JUnit4Mockery();

    @Test
    public void testCallStork() {
        final IMagic magic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(magic);
        IWeather weather = new WeatherMock(LuminaryType.ISNOTSHINING, 3, DaylightType.DAY);

        context.checking(new Expectations() {
	    {
                oneOf(magic).callDaemon();
                oneOf(magic).giveBaby();
            }
        });

        cloud.Create(weather);
    }

    @Test
    public void testCallDaemon() {
        final IMagic magic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(magic);
        IWeather weather = new WeatherMock(LuminaryType.ISSHINING, 0, DaylightType.NIGHT);

        context.checking(new Expectations() {
	    {
                oneOf(magic).callStork();
                oneOf(magic).giveBaby();
            }
        });

        cloud.Create(weather);
    }
}
