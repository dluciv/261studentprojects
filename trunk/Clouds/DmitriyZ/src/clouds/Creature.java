/*
 * Clouds
 * Creature
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

public class Creature {

    CreatureEnum type;

    public Creature(CreatureEnum creature) {
        type = creature;
    }

    public CreatureEnum getType() {
        return type;
    }
}