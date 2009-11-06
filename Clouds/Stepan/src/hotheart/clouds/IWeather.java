/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.clouds;

/**
 *
 * @author m08ksa
 */
public interface IWeather {
    public IWind getWind();
    public IDayLight getDayLight();
    public ILuminary getLuminary();
}
