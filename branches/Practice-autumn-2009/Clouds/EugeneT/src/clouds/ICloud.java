/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

public interface ICloud {

    Creature create(IWeatherFactory weatherFactory);
}
