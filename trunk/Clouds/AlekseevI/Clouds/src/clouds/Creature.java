/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clouds;

/**
 *
 * @author Lii
 */
public class Creature {

    private CreatureType creatureType;

    public Creature(CreatureType creatureType) {
        this.creatureType = creatureType;

    }

    public CreatureType getType() {
        return creatureType;
    }
}
