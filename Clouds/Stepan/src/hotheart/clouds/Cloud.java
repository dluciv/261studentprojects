/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.clouds;

/**
 * @author m08ksa
 */
public class Cloud {

    IWeather weather;

    public Cloud(IWeather weather) {
        this.weather = weather;
    }

    public Creature getCreature() {

        IDayLight daylightPrivider = weather.getDayLight();
        DayLightType daylight = daylightPrivider.getDayLightType();

        IWind wind = weather.getWind();
        int windSpeed = wind.getSpeed();

        if (weather.getLuminary().isShining()) {
            // isLuminary
            if (daylight == DayLightType.NOON) {
                if (windSpeed == 10) {
                    return new Creature(CreatureType.Puppy);
                } else {
                    return null;
                }
            } else if (daylight == DayLightType.EVENING) {
                if ((8 <= windSpeed) && (windSpeed <= 9)) {
                    return new Creature(CreatureType.Kitten);
                } else if ((4 <= windSpeed) && (windSpeed <= 7)) {
                    return new Creature(CreatureType.Piglet);
                } else {
                    return null;
                }
            } else if (daylight == DayLightType.MORNING) {
                if (windSpeed == 5) {
                    return new Creature(CreatureType.Balloon);
                } else {
                    return null;
                }
            } else if (daylight == DayLightType.NIGHT) {
                if ((1 <= windSpeed) && (windSpeed <= 3)) {
                    return new Creature(CreatureType.Hedgehog);
                } else {
                    return null;
                }
            }
        } else {
            // not is Luminary

            if (daylight == DayLightType.NIGHT) {
                if ((1 <= windSpeed) && (windSpeed <= 3)) {
                    return new Creature(CreatureType.Bearcub);
                } else if (0 == windSpeed) {
                    return new Creature(CreatureType.Bat);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        return null;
    }
}
