package cloud;

public class WeatherFactory implements IWeatherFactory {
    private ILuminary luminary = new Luminary();
    private IWind wind = new Wind();
    private IDaylight daylight = new Daylight();

    public ILuminary getLuminary() {
        return luminary;
    }

    public IWind getWind() {
        return wind;
    }

    public IDaylight getDaylightType() {
        return daylight;
    }

}
