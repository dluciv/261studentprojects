/**
 * Table Constructer
 * @author Zubrilin Andrey
 * group 261 (c) 2009
 */

package clouds;

import java.util.LinkedList;

class TableConstructer {

    Herald herald;
    Luminary luminary;
    Wind wMin;
    Wind wMax;
    CreatureType creature;
    Daylight daylight;
 
    TableConstructer(Luminary luminary, Wind wMin, Wind wMax, Daylight daylight,CreatureType creature, Herald herald) {
        this.luminary = luminary;
        this.wMin = wMin;
        this.wMax = wMax;
        this.daylight = daylight;
        this.creature = creature;
        this.herald = herald;
    }
}

public class CreatureTable {

    LinkedList<TableConstructer> list = new LinkedList();

    public CreatureTable() {
        list.add(new TableConstructer(new Luminary(LuminaryType.Shiny), new Wind(0),
                new Wind(1), new Daylight(DaylightType.Noon), CreatureType.Bat, Herald.Stork));
        list.add(new TableConstructer(new Luminary(LuminaryType.NotShiny), new Wind(2),
                new Wind(3), new Daylight(DaylightType.Day), CreatureType.Bear, Herald.Daemon));
        list.add(new TableConstructer(new Luminary(LuminaryType.NotShiny), new Wind(4),
                new Wind(5), new Daylight(DaylightType.Evening), CreatureType.Dog, Herald.Daemon));
        list.add(new TableConstructer(new Luminary(LuminaryType.Shiny), new Wind(6),
                new Wind(7), new Daylight(DaylightType.Night), CreatureType.Kitten, Herald.Stork));
        list.add(new TableConstructer(new Luminary(LuminaryType.NotShiny), new Wind(8),
                new Wind(9), new Daylight(DaylightType.Noon), CreatureType.Piglet, Herald.Stork));
        list.add(new TableConstructer(new Luminary(LuminaryType.Shiny), new Wind(9),
                new Wind(10), new Daylight(DaylightType.Day), CreatureType.Puppy, Herald.Daemon));
    }

    public CreatureType callCreature(IWFactory wFactory) {
        for (TableConstructer TableConstructer : list)
            if ((TableConstructer.luminary.isShiny() == wFactory.luminary().isShiny()) &&
                    (TableConstructer.wMin.current() <= wFactory.wind().current()) &&
                    (TableConstructer.wMax.current() >= wFactory.wind().current()) &&
                    (TableConstructer.daylight.current() == wFactory.daylight().current()))
                return TableConstructer.creature;
        return CreatureType.Bat;
    }

    public Herald callHerald(CreatureType creature) {
        for (TableConstructer TableConstructer : list)
            if (TableConstructer.creature == creature)
                return TableConstructer.herald;
        return Herald.Daemon;
    }
}
