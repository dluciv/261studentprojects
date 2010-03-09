//(с) Кривых Алексей 2009г.
//Cloud
package mult;

//Реализация класса Cloud
public class Cloud implements ICloud {

    private IMagic magic;

    //Конструктор класса Cloud
    public Cloud(IMagic magic) {
        this.magic = magic;
    }

    //Метод выбирает существо в зависимости от погодных условий
    private CreatureType internalCreate(IWeatherFactory weather) {

        ILuminary luminary = weather.luminaryCreate();
        IWind wind = weather.windCreate();
        IDaylight daylight = weather.daylightCreate();

        if (luminary.current() == EnumLuminary.ISSHINING) {
            if (wind.current() == EnumWind.LITTLE &
                daylight.current() == EnumDaylight.MORNING) {
                return CreatureType.BAT;
            } else if (wind.current() == EnumWind.MIDDLE &
                       daylight.current() == EnumDaylight.NOON) {
                return CreatureType.HEDGEHOG;
            } else if (wind.current() == EnumWind.STRONG &
                       daylight.current() == EnumDaylight.EVNING) {
                return CreatureType.PIGLET;
            } else {
                return CreatureType.BALLOON;
            }
        } else if (wind.current() == EnumWind.LITTLE &
                   daylight.current() == EnumDaylight.MORNING) {
            return CreatureType.BEARCUB;
        } else if (wind.current() == EnumWind.MIDDLE &
                   daylight.current() == EnumDaylight.NOON) {
            return CreatureType.KITTEN;
        } else if (wind.current() == EnumWind.STRONG &
                   daylight.current() == EnumDaylight.EVNING) {
            return CreatureType.PUPPY;
        } else {
            return CreatureType.BALLOON;
        }
    }

    //Метод создает существо, вызывает у класса Magic call... и giveBaby
    public Creature create(IWeatherFactory weather) {

        CreatureType type = internalCreate(weather);
        Creature creature = new Creature(type);

        if (type == CreatureType.BAT | type == CreatureType.HEDGEHOG |
            type == CreatureType.PUPPY) {
            magic.callStork();
        } else {
            magic.callDaemon();
        }

        magic.giveBaby(type);

        return creature;
    }
}
