//by Skorodumov Kirill gr: 261

package clouds;

class Cloud

{

    private IDaylight daylight = new Daylight();
    private ILuminary luminary = new Luminary();
    private IWind wind = new Wind();
    
    public Cloud(IWeatherFactory factory) {
    	daylight = factory.createDaylight();
    	wind = factory.createWind();
    	luminary = factory.createLuminary();
    }

 

    private Creature InternalCreate() {
      
      if (daylight.getDaylight() == DaylightType.Morning) {
    	  if (luminary.isShining()) {
    		  if (wind.getSpeed() == 0) {
    			  return new Creature(CreatureType.Baloon);
    		  }
    	  }
      }

      if (daylight.getDaylight() == DaylightType.Morning){
    	  if (luminary.isShining()) {
    		  if ((wind.getSpeed() > 0) && (wind.getSpeed() <= 5)) {
    			  return new Creature(CreatureType.Bearcub);
    		  }
    	  }
      }
      
      if (daylight.getDaylight() == DaylightType.Morning) {
    	  if (luminary.isShining()) {
    		  if ((wind.getSpeed() > 5) && (wind.getSpeed() <= 10)) {
    			  return new Creature(CreatureType.Piglet);
    		  }
    	  }
      }
      
      if (daylight.getDaylight() == DaylightType.Morning) {
    	  if (!luminary.isShining()) {
    		  if ((wind.getSpeed() >= 0) && (wind.getSpeed() <= 5)) {
    			  return new Creature(CreatureType.Puppy);
    		  } else return new Creature(CreatureType.Kitten);
    	  }
      }
      
      if (daylight.getDaylight() == DaylightType.Noon) {
    	  if (luminary.isShining()) {
    		  if (wind.getSpeed() < 6) {
    			  return new Creature(CreatureType.Hedgehog);
    		  } else return new Creature(CreatureType.Bearcub);
    	  }
    	  
    	  if (!luminary.isShining()) {
    		  if (wind.getSpeed() < 5) {
    			  return new Creature(CreatureType.Puppy);
    		  } else if (wind.getSpeed() < 7) {
    			  return new Creature(CreatureType.Piglet);
    		  } else return new Creature(CreatureType.Baloon);
    	  }
      }
      
      if (daylight.getDaylight() == DaylightType.Evening) {
    	  if (luminary.isShining()) {
    		  if (wind.getSpeed() < 5) {
    			  return new Creature(CreatureType.Piglet);
    		  } else return new Creature(CreatureType.Kitten);
    	  }
    	  
    	  if (!luminary.isShining()) {
    		  if (wind.getSpeed() < 5) {
    			  return new Creature(CreatureType.Baloon);
    		  } else return new Creature(CreatureType.Hedgehog);
    	  }
      }
      
      if (daylight.getDaylight() == DaylightType.Night) {
    	  if (luminary.isShining()) {
    		  if (wind.getSpeed() < 5) {
    			  return new Creature(CreatureType.Bat);
    		  } else return new Creature(CreatureType.Bearcub);
    	  }
    	  
    	  if (!luminary.isShining()) {
    		  if (wind.getSpeed() < 5) {
    			  return new Creature(CreatureType.Bat);
    		  } else return new Creature(CreatureType.Piglet);
    	  }
      }
      return null;

}

 

    public Creature Create()

    {

      Creature creature = InternalCreate();

      IMagic magic = null;

      if ((creature.getType() == CreatureType.Baloon) || (creature.getType() == CreatureType.Bat)
    		  || (creature.getType() == CreatureType.Bearcub) 
    		  || (creature.getType() == CreatureType.Hedgehog)) {
    	  magic.callDaemon(creature.getType());
      } else magic.callStork(creature.getType());

      return creature;

    }

}