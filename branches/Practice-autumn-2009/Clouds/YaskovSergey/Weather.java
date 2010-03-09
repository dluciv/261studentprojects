/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package clouds;

public class Weather implements IWeather {
    private Luminary luminary;
    private Wind wind;
    private Daylight daylight;

    Weather () {
        luminary = new Luminary();
        wind = new Wind();
        daylight = new Daylight();
    }

    public IDaylight getDaylight() {
        return daylight;
    }

    public ILuminary getLuminary() {
        return luminary;
    }

    public IWind getWind() {
        return wind;
    }
}
