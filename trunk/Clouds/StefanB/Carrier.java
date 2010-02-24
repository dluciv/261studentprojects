/*
 * (c) Stefan Bojarovski 2010
 * */
package cloud;

public class Carrier {
	
	private CarrierEnumType type;
	private Creature creature = null;
	
	public Carrier(CarrierEnumType type){
		this.type = type;
	}
	
	public CarrierEnumType getCarrierType(){
		return type;
	}
	
	public void giveCreature(Creature creature){
		this.creature = creature;
	}
	
	public CreatureEnumType getCreatureType(){
		return creature.getType();
	}
}
