package cloud;

public class Creature {
   private CreatureType creature;
    public Creature(CreatureType creature){
        this.creature = creature;
    }
public CreatureType getCreatureType(){
    return creature;
}
}