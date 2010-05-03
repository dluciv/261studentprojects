//(с) Кривых Алексей 2009г.
//Cloud
package mult;

import org.junit.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.*;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class MagicMock {

    Cloud cloud = null;
    Mockery mockery = new JUnit4Mockery();

    @Test
    public void testCloudCallMagic() {
        
        final IMagic magic = mockery.mock(IMagic.class);
        cloud = new Cloud(magic);
        IWeatherFactory weather = new WeatherFactoryMock(EnumWind.LITTLE,
                EnumDaylight.MORNING, EnumLuminary.ISSHINING);
        
        //Проверяем вызвались ли методы call... и giveBaby с нужным параметром класса Magic
        mockery.checking(new Expectations() {
            {
                oneOf(magic).callStork();
                oneOf(magic).giveBaby(CreatureType.BAT);
            }
        });

        cloud.create(weather);
    }
}
