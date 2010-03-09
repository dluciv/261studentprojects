/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

public class WeatherFactoryMock implements IWeatherFactory {

        private Daylight daylight;
        private Luminary luminary;
        private Wind wind;

        public WeatherFactoryMock (LuminaryType luminaryType, int strength, DaylightType daylightType) {
            luminary = new Luminary(luminaryType);
            wind = new Wind(strength);
            daylight = new Daylight(daylightType);
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
