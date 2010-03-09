/**
 * MockFactory
 * @author Zubrilin Andrey
 * group 261 (c) 2009
 */

package clouds;

public class WFacMock implements IWFactory {

        private Daylight daylight;
        private Wind wind;
        private Luminary luminary;
        

        public WFacMock (LuminaryType luminaryType, int str, DaylightType daylightType) {
            luminary = new Luminary(luminaryType);
            wind = new Wind(str);
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
