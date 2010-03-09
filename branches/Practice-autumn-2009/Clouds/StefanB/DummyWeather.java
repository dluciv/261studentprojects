/*
 * (c) Stefan Bojarovski 2009
 * */
package cloud;

/*
 * This class allows to create a mock of the Weather class
 * where we can create custom weather conditions
 * so that we can test the creation of creatures
 * */
public class DummyWeather implements IWeather {
	
	private DaylightEnumType daylight;
    private boolean luminary;
    private int windSpeed;

    public DummyWeather(boolean luminary, DaylightEnumType daylight, int speed) {
        this.daylight = daylight;
        this.luminary = luminary;
        this.windSpeed = speed;
    }
    
    private class DummyDaylight implements IDaylight {

        public DaylightEnumType current() {
            return daylight;
        }
    }

    private class DummyLuminary implements ILuminary {

        public boolean isShiny() {
            return luminary;
        }
    }

    private class DummyWind implements IWind{

        public int getWindSpeed() {
            return windSpeed;
        }
    }

	public IDaylight getDaylight(){
		return new DummyDaylight();
	}
	public ILuminary getLuminary(){
		return new DummyLuminary();
	}
	public IWind getWind(){
		return new DummyWind();
	}

}
