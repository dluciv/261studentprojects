/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

public class Daylight implements IDaylight {

    private DaylightType daylightType;

    public Daylight(DaylightType daylightType) {
        this.daylightType = daylightType;
    }

    public DaylightType current() {
        return daylightType;
    }
}
