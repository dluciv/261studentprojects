/*
 * Magic class do nothing 
 * 'cause this project only for Unit Tests
 * Savenko Maria ©2009
 */

package msavenko;

import msavenko.ICreature.CreatureType;

public class Magic implements IMagic {

	@Override
	public void CallDemon(CreatureType creatureType) {

	}

	@Override
	public void CallStork(CreatureType creatureType) {

	}

	@Override
	public void GiveBaby(ICreature creature) {
	}

}
