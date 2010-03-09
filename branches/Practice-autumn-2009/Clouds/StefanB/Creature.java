/*
 * (c) Stefan Bojarovski 2009
 * */
package cloud;

public class Creature {
	CreatureEnumType type;
	
	public Creature(CreatureEnumType type){
		this.type = type;
	}
	
	public CreatureEnumType getType(){
		return type;
	}

}
