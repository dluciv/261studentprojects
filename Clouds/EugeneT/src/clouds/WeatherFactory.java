/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

import java.util.Random;

public class WeatherFactory implements IWeatherFactory {

    private static final int MAX_WIND_STRENGTH = 10;
    private Daylight daylight;
    private Luminary luminary;
    private Wind wind;

    public WeatherFactory() {

        Random rnd = new Random();

        if (rnd.nextBoolean()) {
            luminary = new Luminary(LuminaryType.IsShiny);
        } else {
            luminary = new Luminary(LuminaryType.IsNotShiny);
        }

        switch (rnd.nextInt(DaylightType.values().length)) {
            case 0:
                daylight = new Daylight(DaylightType.Day);
                break;
            case 1:
                daylight = new Daylight(DaylightType.Noon);
                break;
            case 2:
                daylight = new Daylight(DaylightType.Evening);
                break;
            case 3:
                daylight = new Daylight(DaylightType.Night);
                break;
        }

        wind = new Wind(rnd.nextInt(MAX_WIND_STRENGTH));
    }

    public IDaylight daylight() {
        return daylight;
    }

    public ILuminary luminary() {
        return luminary;
    }

    public IWind wind() {
        return wind;
    }
}
