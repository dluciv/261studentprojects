/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

public interface IWeatherFactory {

    IDaylight daylight();

    ILuminary luminary();

    IWind wind();
}
