/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.clouds;

/**
 *
 * @author m08ksa
 */
public class Cloud {
    
    IWeather weather;
    public Cloud(IWeather weather)
    {
        this.weather = weather;
    }
    
    public Creature getCreature() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
