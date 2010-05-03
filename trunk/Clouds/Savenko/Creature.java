/*
 * Creature class simply creates creature by it's type
 * Savenko Maria ©2009
 */

package msavenko;

public class Creature implements ICreature {

	public CreatureType creature;

	public Creature(CreatureType creatureType) {
		creature = creatureType;
	}

	public CreatureType getCreatureType() {
		return creature;
	}

}
