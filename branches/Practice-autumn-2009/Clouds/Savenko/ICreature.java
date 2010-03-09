/*
 * Interface for Creature class
 * have list of animals
 * Savenko Maria ©2009
 */

package msavenko;

public interface ICreature {
	
	public enum CreatureType {Puppy,Kitten,Hedgehog,Bearcub,Piglet,Bat,Balloon};
	
	public CreatureType getCreatureType();
	
}
