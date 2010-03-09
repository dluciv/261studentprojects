/*
 * Interface for Magic class
 * Savenko Maria ©2009
 */

package msavenko;

import msavenko.ICreature.CreatureType;

public interface IMagic {
	
	public void CallStork(CreatureType creatureType);
	
	public void CallDemon(CreatureType creatureType);
	
	public void GiveBaby(ICreature creature);
}
