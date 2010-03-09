package clouds;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.*;
import org.junit.runner.RunWith;
import org.junit.*;

@RunWith(JMock.class)
public class MagicMock {

    Cloud cloud = null;
    Mockery mockery = new JUnit4Mockery();

    @Test
    public void testCloudMagicCall() {
        final IMagic magic = mockery.mock(IMagic.class);
        cloud = new Cloud(magic);
        IFuctoryWeather weather = new MockFuctoryWheather(EnumDaylight.MORNING, EnumLuminary.ISSHINY, EnumWinds.STORM);
        mockery.checking(new Expectations() {

            {
                oneOf(magic).callStork();
                oneOf(magic).giveBaby(CreatureType.PUPPY);
            }
        });
        cloud.create(weather);
    }
}
