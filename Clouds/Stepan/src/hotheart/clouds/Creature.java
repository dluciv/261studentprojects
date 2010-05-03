/*
 * Creature class by Korshakov Stepan
 * Group 261 - 2009 (c)
 */

package hotheart.clouds;

/**
 *
 * @author Korshakov Stepan
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
