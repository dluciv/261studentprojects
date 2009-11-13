/*
 * Cloud class by Korshakov Stepan
 * Group 261 - 2009(c)
 */
package hotheart.clouds;

/**
 * @author Korshakov Stepan
 */
public class Cloud {

    IWeather weather;
    IMagic magic;

    public Cloud(IWeather weather, IMagic magic) {
        this.weather = weather;
        this.magic = magic;
    }

    public BabyCarrier getCreature() {

        IDayLight daylightPrivider = weather.getDayLight();
        DayLightType daylight = daylightPrivider.getDayLightType();

        IWind wind = weather.getWind();
        int windSpeed = wind.getSpeed();

        Creature result = null;
        BabyCarrier carrier = null;

        if (weather.getLuminary().isShining()) {
            // isLuminary
            if (daylight == DayLightType.NOON) {
                if (windSpeed == 10) {
                    result = new Creature(CreatureType.Puppy);
                    carrier = magic.CallDaemon();
                } else {
                    return null;
                }
            } else if (daylight == DayLightType.EVENING) {
                if ((8 <= windSpeed) && (windSpeed <= 9)) {
                    result = new Creature(CreatureType.Kitten);
                    carrier = magic.CallStork();
                } else if ((4 <= windSpeed) && (windSpeed <= 7)) {
                    result = new Creature(CreatureType.Piglet);
                    carrier = magic.CallDaemon();
                } else {
                    return null;
                }
            } else if (daylight == DayLightType.MORNING) {
                if (windSpeed == 5) {
                    result = new Creature(CreatureType.Balloon);
                    carrier = magic.CallStork();
                } else {
                    return null;
                }
            } else if (daylight == DayLightType.NIGHT) {
                if ((1 <= windSpeed) && (windSpeed <= 3)) {
                    result = new Creature(CreatureType.Hedgehog);
                    carrier = magic.CallStork();
                } else {
                    return null;
                }
            }
        } else {
            // not is Luminary

            if (daylight == DayLightType.NIGHT) {
                if ((1 <= windSpeed) && (windSpeed <= 3)) {
                    result = new Creature(CreatureType.Bearcub);
                    carrier = magic.CallDaemon();
                } else if (0 == windSpeed) {
                    result = new Creature(CreatureType.Bat);
                    carrier = magic.CallDaemon();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        
        carrier.giveBaby(result);

        return carrier;
    }
}
