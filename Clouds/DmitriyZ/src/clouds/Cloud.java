/*
 * Clouds
 * Cloud
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

public class Cloud implements ICloud {

    private IMagic magic;
    private IWeatherFactory weather;

    public Cloud(IMagic magic, IWeatherFactory weather) {
        this.magic = magic;
        this.weather = weather;
    }

    private CreatureEnum internalCreate() {
        ILuminary luminary = weather.luminary();
        IWind wind = weather.wind();
        IDaylight daylight = weather.daylight();

        if (luminary.current() == LuminaryEnum.ISSHINING) {
            if (wind.current() <= 6 && wind.current() >= 5 && daylight.current() == DaylightEnum.EVENING) {
                return CreatureEnum.Puppy;
            } else if (wind.current() <= 4 && wind.current() >= 3 && daylight.current() == DaylightEnum.EVENING) {
                return CreatureEnum.Kitten;
            } else if (wind.current() <= 10 && wind.current() >= 9 && daylight.current() == DaylightEnum.NOON) {
                return CreatureEnum.Hedgehog;
            } else if (wind.current() <= 8 && wind.current() >= 5 && daylight.current() == DaylightEnum.NOON) {
                return CreatureEnum.Bearcub;
            }
        } else if (luminary.current() == LuminaryEnum.NOTSHINING) {
            if (wind.current() <= 4 && wind.current() >= 1 && daylight.current() == DaylightEnum.NOON) {
                return CreatureEnum.Piglet;
            } else if (wind.current() <= 5 && wind.current() >= 3 && daylight.current() == DaylightEnum.NIGHT) {
                return CreatureEnum.Bat;
            } else if (wind.current() == 0 && daylight.current() == DaylightEnum.MORNING) {
                return CreatureEnum.Balloon;
            }
        }
        return CreatureEnum.Balloon;
    }

    public Creature create() {
        CreatureEnum type = internalCreate();
        Creature creature = new Creature(type);
        if (type == CreatureEnum.Piglet | type == CreatureEnum.Hedgehog | type == CreatureEnum.Puppy | type == CreatureEnum.Kitten) {
            magic.callStork();
        } else {
            magic.callDaemon();
        }

        magic.giveBaby(type);
        return creature;
    }
}
