/*
 * (c) Stefan Bojarovski 2010
 * */
package cloud;

public class Cloud implements ICloud {
	private IMagic magic;
	private IWeather weather;
	
	public Cloud (IMagic magic, IWeather weather){
		this.magic = magic;
		this.weather = weather;
	}
	
	private CreatureEnumType internalCreate(){
		
		ILuminary luminary = weather.getLuminary();
		IDaylight daylight = weather.getDaylight();
		IWind wind = weather.getWind();
		
		int windSpeed = wind.getWindSpeed();
		boolean isShiny = luminary.isShiny();
		DaylightEnumType timeDay = daylight.current();
		
		//big chunk of if statements here
		if (windSpeed >= 0 && windSpeed <= 5 ){
			if (isShiny){
				switch(timeDay){
					case Morning:
						return CreatureEnumType.Puppy;
					case Noon:
						return CreatureEnumType.Kitten;
					case Evening:
						return CreatureEnumType.Hedgehog;
					case Night:
						return CreatureEnumType.Bearcub;
				}
			}
			else{
				switch(timeDay){
					case Morning:
						return CreatureEnumType.Piglet;
					case Noon:
						return CreatureEnumType.Bat;
					case Evening:
						return CreatureEnumType.Balloon;
					case Night:
						return CreatureEnumType.Puppy;				
				}
			}
		}
		//if windSpeed is between 5-10
		else if (windSpeed <= 10) {
			if (isShiny){
				switch(timeDay){
					case Morning:
						return CreatureEnumType.Kitten;
					case Noon:
						return CreatureEnumType.Hedgehog;
					case Evening:
						return CreatureEnumType.Bearcub;
					case Night:
						return CreatureEnumType.Piglet;
				}
			}
			else{
				switch(timeDay){
					case Morning:
						return CreatureEnumType.Bat;
					case Noon:
						return CreatureEnumType.Balloon;
					case Evening:
						return CreatureEnumType.Puppy;
					case Night:
						return CreatureEnumType.Kitten;				
				}
			}			
		}
		return null;
	}
	
	public Carrier create(){
		
		CreatureEnumType type = internalCreate();
		Creature creature = new Creature(type);
		Carrier carrier = null;
		
		//call stork or deamon
		if (type == CreatureEnumType.Balloon || type == CreatureEnumType.Kitten 
				|| type == CreatureEnumType.Puppy ||type == CreatureEnumType.Piglet)
		{
			carrier = magic.callStork();
		}
		else{
			carrier = magic.callDeamon();
			}
		carrier.giveCreature(creature);
		return carrier;
	}

}
