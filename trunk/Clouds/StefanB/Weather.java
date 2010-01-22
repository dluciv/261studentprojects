package cloud;

public class Weather implements IWeather {
	
	private ILuminary luminary = new Luminary();
	private IDaylight daylight = new Daylight();
	private IWind  wind = new Wind();
	
	public ILuminary getLuminary(){
		return luminary;
	}
	public IDaylight getDaylight(){
		return daylight;
	}
	public IWind getWind(){
		return wind;
	}

}
