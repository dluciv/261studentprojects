/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package cloudstests;

import clouds.*;

public class WeatherMock implements IWeather {
    private Daylight daylight = new Daylight();
    private Wind wind = new Wind();
    private Luminary luminary = new Luminary();


    public WeatherMock (LuminaryType luminaryType, int windPower, DaylightType daylightType) {
        luminary.luminaryType = luminaryType;
        wind.windPower = windPower;
        daylight.daylightType = daylightType;
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
