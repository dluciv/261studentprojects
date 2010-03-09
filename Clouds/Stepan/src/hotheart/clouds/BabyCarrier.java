/*
 * IBabyCarier class by Korshakov Stepan
 * 261 Group - 2009 (c)
 */
package hotheart.clouds;

/**
 *
 * @author m08ksa
 */
public class BabyCarrier {

    Creature creature = null;
    BabyCarrierType type;

    public BabyCarrier(BabyCarrierType type) {
        this.type = type;
    }

    public BabyCarrierType getType() {
        return type;
    }

    public void giveBaby(Creature creature) {
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }
}
