/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clouds;

/**
 *
 * @author Lii
 */
public class FuctoryWeather implements IFuctoryWeather {

    public Wind createWind() {
        return new Wind();

    }

    public Daylight createDaylight() {
        return new Daylight();
    }

    public Luminary createLuminary() {
        return new Luminary();
    }
}
