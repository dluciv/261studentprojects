//(с) Кривых Алексей 2009г.
//Cloud
package mult;

public class WeatherFactoryMock implements IWeatherFactory {

    private EnumWind wind;
    private EnumDaylight daylight;
    private EnumLuminary luminary;

    public WeatherFactoryMock(EnumWind wind, EnumDaylight daylight,
            EnumLuminary luminary) {
        this.wind = wind;
        this.daylight = daylight;
        this.luminary = luminary;
    }

    public class WindMock implements IWind {

        public EnumWind current() {
            return wind;
        }
    }

    public class LuminaryMock implements ILuminary {

        public EnumLuminary current() {
            return luminary;
        }
    }

    public class DaylightMock implements IDaylight {

        public EnumDaylight current() {
            return daylight;
        }
    }

    public IWind windCreate() {
        return new WindMock();
    }

    public IDaylight daylightCreate() {
        return new DaylightMock();
    }

    public ILuminary luminaryCreate() {
        return new LuminaryMock();
    }
}
