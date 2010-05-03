package cloud;

import cloud.IDaylight.DaylightType;
import cloud.ILuminary.LuminaryType;

public class Cloud implements ICloud {
    private IMagic magic;
    private IWeatherFactory weather;
    public Cloud(IMagic magic,IWeatherFactory weather){
      this.magic = magic;
      this.weather = weather;
    }

  private CreatureType internalCreate() {
    if (weather.getWind().generateWindForm() == 1 && weather.getDaylightType().generateDaylightType() == DaylightType.Day
            && weather.getLuminary().isShining() == LuminaryType.shining){
        return CreatureType.Bat; 
    }else{
  return  CreatureType.Kitten;
  }
  }
    public Creature CallDeliverer() {
       Creature creature = new Creature(internalCreate());
        if (creature.getCreatureType()==CreatureType.Puppy && creature.getCreatureType()==CreatureType.Kitten && creature.getCreatureType()==CreatureType.Hedgehog){
            magic.callDaemon();
        }else{
            magic.callStork();
        }
        magic.giveBaby();
        return creature;
    }

}

