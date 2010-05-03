/*
 * Clouds
 * IWeatherFactory
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

public interface IWeatherFactory {

    IDaylight daylight();

    ILuminary luminary();

    IWind wind();
}
