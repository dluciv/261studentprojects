/*
 * class Cloud creates creatures according to WindPower, luminary and DayLight,
 * then calls demon or stork and gives 'em a baby 
 * Savenko Maria ©2009
 */

package msavenko;

import msavenko.ICreature.CreatureType;
import msavenko.IDaylight.DaylightType;

public class Cloud implements ICloud {
    
    private IDaylight daylight;
    private ILuminary luminary;
    private IWind     wind;
    //private IMagic    magic;
    
    public Cloud(IWeatherFactory weatherFactory) {
        daylight = weatherFactory.CreateDaylight();
        luminary = weatherFactory.CreateLuminary();
        wind = weatherFactory.CreateWind();
    }
    
    @Override
    public Creature Create() {
        IMagic magic = new Magic();
        Creature creature = InternalCreate();
        if (creature.getCreatureType() == CreatureType.Bat
                || creature.getCreatureType() == CreatureType.Balloon
                || creature.getCreatureType() == CreatureType.Hedgehog)
            magic.CallDemon(creature.getCreatureType());
        else magic.CallStork(creature.getCreatureType());
        
        magic.GiveBaby(creature);
        
        return creature;
    }
    
    private Creature InternalCreate() {
        boolean isShining = luminary.IsShining();
        int windPower = wind.getWindPower();
        DaylightType daylightType = daylight.getDaylight();
        
        if (isShining){/*
            switch (daylightType){
                case DaylightType.Morning:
                    return inTheMorning(windPower);
                case DaylightType.Noon:
                    return inTheNoon(windPower);
                case DaylightType.Evening:
                    return inTheEvening(windPower);
                case DaylightType.Night:
                    return inTheNight(windPower);
            }*/
            if (windPower <= 4){
                if (daylightType == DaylightType.Evening)
                    return new Creature(CreatureType.Piglet);
                else
                    if (daylightType == DaylightType.Noon)
                        return new Creature(CreatureType.Puppy);
                    else return new Creature(CreatureType.Kitten);
            }
            else
                if (windPower <= 9){
                    if (daylightType == DaylightType.Morning)
                        return new Creature(CreatureType.Puppy);
                    else
                        if (daylightType == DaylightType.Night)
                            return new Creature(CreatureType.Piglet);
                        else return new Creature(CreatureType.Bearcub);
                }
                else{
                    if (daylightType == DaylightType.Night)
                        return new Creature(CreatureType.Bat);
                    else return new Creature(CreatureType.Kitten);
                }
        }
        else{
            if (windPower <= 6){
                if (daylightType == DaylightType.Morning
                        || daylightType == DaylightType.Noon)
                    return new Creature(CreatureType.Hedgehog);
                else return new Creature(CreatureType.Balloon);
            }
            else{
                if (daylightType == DaylightType.Morning
                        || daylightType == DaylightType.Noon)
                    return new Creature(CreatureType.Balloon);
                else return new Creature(CreatureType.Hedgehog);
            }
        }
        
    }
    
    private Creature inTheMorning(int windPower) {
        
        if (windPower <= 4 || windPower == 10){
            return new Creature(CreatureType.Kitten);
        }
        else{
            return new Creature(CreatureType.Puppy);
        }
    }
    
    private Creature inTheNoon(int windPower) {
        if (windPower <= 4){
            return new Creature(CreatureType.Puppy);
        }
        else
            if (windPower <= 9){
                return new Creature(CreatureType.Bearcub);
            }
            else{
                return new Creature(CreatureType.Kitten);
            }
    }
    
    private Creature inTheEvening(int windPower) {
        if (windPower <= 4){
            return new Creature(CreatureType.Piglet);
        }
        else
            if (windPower <= 9){
                return new Creature(CreatureType.Bearcub);
            }
            else{
                return new Creature(CreatureType.Kitten);
            }
    }
    
    private Creature inTheNight(int windPower) {
        if (windPower <= 4){
            return new Creature(CreatureType.Kitten);
        }
        else
            if (windPower <= 9){
                return new Creature(CreatureType.Piglet);
            }
            else{
                return new Creature(CreatureType.Bat);
            }
    }
    
}
