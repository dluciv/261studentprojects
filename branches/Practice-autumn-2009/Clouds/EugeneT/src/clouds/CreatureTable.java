/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

import java.util.LinkedList;
import java.util.List;

class StringTable {

    Luminary luminary;
    Wind windMin;
    Wind windMax;
    Daylight daylight;
    Creature creature;
    Courier courier;

    StringTable(Luminary luminary, Wind windMin, Wind windMax, Daylight daylight,
            Creature creature, Courier courier) {
        this.luminary = luminary;
        this.windMin = windMin;
        this.windMax = windMax;
        this.daylight = daylight;
        this.creature = creature;
        this.courier = courier;
    }
}

public class CreatureTable {

    List<StringTable> table = new LinkedList();

    public CreatureTable() {
        table.add(new StringTable(new Luminary(LuminaryType.IsShiny), new Wind(4),
                new Wind(7), new Daylight(DaylightType.Evening), Creature.Puppy, Courier.Stork));
        table.add(new StringTable(new Luminary(LuminaryType.IsShiny), new Wind(3),
                new Wind(8), new Daylight(DaylightType.Noon), Creature.Kitten, Courier.Stork));
        table.add(new StringTable(new Luminary(LuminaryType.IsNotShiny), new Wind(5),
                new Wind(10), new Daylight(DaylightType.Day), Creature.Hedgehog, Courier.Daemon));
        table.add(new StringTable(new Luminary(LuminaryType.IsShiny), new Wind(2),
                new Wind(9), new Daylight(DaylightType.Evening), Creature.Bearcub, Courier.Daemon));
        table.add(new StringTable(new Luminary(LuminaryType.IsNotShiny), new Wind(1),
                new Wind(4), new Daylight(DaylightType.Day), Creature.Piglet, Courier.Stork));
        table.add(new StringTable(new Luminary(LuminaryType.IsNotShiny), new Wind(8),
                new Wind(10), new Daylight(DaylightType.Night), Creature.Bat, Courier.Daemon));
        table.add(new StringTable(new Luminary(LuminaryType.IsShiny), new Wind(0),
                new Wind(0), new Daylight(DaylightType.Noon), Creature.Ballon, Courier.Stork));
    }

    public Creature getCreatureFromTable(IWeatherFactory weatherFactory) {
        for (StringTable stringTable : table) {
            if ((stringTable.luminary.current() == weatherFactory.luminary().current()) &&
                    (stringTable.windMax.current() >= weatherFactory.wind().current()) &&
                    (stringTable.windMin.current() <= weatherFactory.wind().current()) &&
                    (stringTable.daylight.current() == weatherFactory.daylight().current())) {
                return stringTable.creature;
            }
        }

        return Creature.Ballon;
    }

    public Courier getCourierFromTable(Creature creature) {
        for (StringTable stringTable : table) {
            if (stringTable.creature == creature) {
                return stringTable.courier;
            }
        }

        return Courier.Stork;
    }
}
