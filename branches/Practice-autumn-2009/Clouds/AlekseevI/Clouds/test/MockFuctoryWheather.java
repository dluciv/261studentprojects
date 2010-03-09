package clouds;


import clouds.*;

public class MockFuctoryWheather implements IFuctoryWeather {

    private EnumDaylight daylight;
    private EnumLuminary luminary;
    private EnumWinds wind;

    public MockFuctoryWheather(EnumDaylight daylight, EnumLuminary luminary, EnumWinds wind) {
        this.daylight = daylight;
        this.luminary = luminary;
        this.wind = wind;
    }

    public IWind createWind() {
        return new WindMock();
    }

    public IDaylight createDaylight() {
        return new DayLightMock();
    }

    public ILuminary createLuminary() {
        return new LuminaryMock();
    }

    private class WindMock implements IWind {

        public EnumWinds current() {
            return wind;
        }
    }

    private class LuminaryMock implements ILuminary {

        public EnumLuminary current() {
            return luminary;
        }
    }

    private class DayLightMock implements IDaylight {

        public EnumDaylight current() {
            return daylight;
        }
    }
}
