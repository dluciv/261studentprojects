/*
 * Interface for Luminary class
 * Antonov Kirill 2009
 */

package clouds;

import clouds.ICreature.CreatureType;


public interface IMagic {

	public void CallStork(CreatureType creatureType);

	public void CallDemon(CreatureType creatureType);

	public void GiveBaby(ICreature creature);
}