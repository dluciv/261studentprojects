/*
 * Creature class simply creates creature by it's type
 * Antonov Kirill 2009
 */

package clouds;

public class Creature implements ICreature {

	public CreatureType creature;

	public Creature(CreatureType creatureType) {
		creature = creatureType;
	}

	public CreatureType getCreatureType() {
		return creature;
	}

}