/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package clouds;

public class Creature {
    public CreatureType creatureType;
    public DelivererType itsDelivererType;

    Creature(CreatureType toCreate) {
        creatureType = toCreate;
    }
}
