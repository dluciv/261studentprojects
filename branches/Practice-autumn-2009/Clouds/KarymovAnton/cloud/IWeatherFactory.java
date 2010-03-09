package cloud;

public interface IWeatherFactory {
    public ILuminary getLuminary();
    public IWind getWind();
    public IDaylight getDaylightType();
}
