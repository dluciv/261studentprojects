/*
 *  Interface for Creature class
 * have list of animals
 * Antonov Kirill 2009
 */

package clouds;


public interface ICreature {

	public enum CreatureType {Puppy,Kitten,Hedgehog,Bearcub,Piglet,Bat,Balloon};

	public CreatureType getCreatureType();

}

