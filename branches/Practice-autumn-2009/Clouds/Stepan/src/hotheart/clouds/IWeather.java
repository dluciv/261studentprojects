/*
 * IWeather interface by Korshakov Stepan
 * 261 Group - 2009 (c)
 */

package hotheart.clouds;

/**
 *
 * @author Korshakov Stepan
 */
public interface IWeather {
    public IWind getWind();
    public IDayLight getDayLight();
    public ILuminary getLuminary();
}
