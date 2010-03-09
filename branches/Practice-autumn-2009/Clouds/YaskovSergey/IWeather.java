/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package clouds;

public interface IWeather {
    IDaylight getDaylight();
    ILuminary getLuminary();
    IWind getWind();
}
