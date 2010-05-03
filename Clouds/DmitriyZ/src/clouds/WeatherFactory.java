/*
 * Clouds
 * Weather Factory
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

public class WeatherFactory implements IWeatherFactory {

    public IDaylight daylight() {
        return new Daylight();
    }

    public ILuminary luminary() {
        return new Luminary();
    }

    public IWind wind() {
        return new Wind();
    }
}