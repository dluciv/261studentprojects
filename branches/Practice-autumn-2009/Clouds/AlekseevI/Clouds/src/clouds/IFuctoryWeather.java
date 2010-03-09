/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clouds;

/**
 *
 * @author Lii
 */
public interface IFuctoryWeather {
    public Wind createWind ();
    public Daylight createDaylight();
    public Luminary createLuminary();

}
