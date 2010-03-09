/*
 * Clouds
 * Mock Weather Factory
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

public class MockWeatherFactory implements IWeatherFactory {

    private DaylightEnum daylight;
    private LuminaryEnum luminary;
    private int wind;

    public MockWeatherFactory(LuminaryEnum luminary, DaylightEnum daylight, int strength) {
        this.luminary = luminary;
        this.wind = strength;
        this.daylight = daylight;
    }

    private class MockDaylight implements IDaylight {

        public DaylightEnum current() {
            return daylight;
        }
    }

    private class MockLuminary implements ILuminary {

        public LuminaryEnum current() {
            return luminary;
        }
    }

    private class MockWind implements IWind{

        public int current() {
            return wind;
        }
    }

    public IDaylight daylight() {
        return new MockDaylight();
    }

    public ILuminary luminary() {
        return new MockLuminary();
    }

    public IWind wind() {
        return new MockWind();
    }
}
