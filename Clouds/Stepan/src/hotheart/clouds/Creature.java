/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.clouds;

/**
 *
 * @author m08ksa
 */
public class Creature {
    
    CreatureType tp;
    public Creature(CreatureType type)
    {
        tp = type;
    }

    public CreatureType getCreatureType()
    {
        return tp;
    }
}
