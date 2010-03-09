/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clouds;

/**
 *
 * @author Lii
 */
public class Cloud implements ICloud {

    private IMagic magic;

    public Cloud(IMagic magic) {
        this.magic = magic;
    }

    private Creature internalCreature(IFuctoryWeather fuctoryWeather) {

        IWind wind = fuctoryWeather.createWind();
        IDaylight daylight = fuctoryWeather.createDaylight();
        ILuminary luminary = fuctoryWeather.createLuminary();


        if (luminary.current() == EnumLuminary.ISSHINY) {
            if (daylight.current() == EnumDaylight.MORNING) {
                return new Creature(CreatureType.PUPPY);
            } else if (daylight.current() == EnumDaylight.NIGHT) {
                return new Creature(CreatureType.BEARCUB);
            } else {
                return new Creature(CreatureType.BALLOON);
            }
        } else if (wind.current() == EnumWinds.STORM) {
            if (daylight.current() == EnumDaylight.NOON) {
                return new Creature(CreatureType.KITTEN);
            } else {
                return new Creature(CreatureType.PIGLET);
            }
        } else if (daylight.current() == EnumDaylight.EVENING) {
            return new Creature(CreatureType.HEDGEHOG);
        } else {
            return new Creature(CreatureType.BAT);
        }

    }

    public Creature create(IFuctoryWeather fuctoryWeather) {
        Creature creature = internalCreature(fuctoryWeather);
        if (creature.getType() == CreatureType.BALLOON ||
                creature.getType() == CreatureType.PIGLET ||
                creature.getType() == CreatureType.PUPPY ||
                creature.getType() == CreatureType.HEDGEHOG) {
            magic.callStork();
        } else {
            magic.callDemon();
        }
        magic.giveBaby(creature.getType());
        return creature;
    }

}