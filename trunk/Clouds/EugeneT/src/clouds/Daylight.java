/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

public class Daylight implements IDaylight {

    private DaylightType daylight;

    public Daylight(DaylightType daylightType) {
        daylight = daylightType;
    }

    public DaylightType current() {
        return daylight;
    }
}
