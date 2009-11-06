/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.clouds;

/**
 * @author m08ksa
 */
public class Cloud {
    
//    public static final TableRow[] creatures = new TableRow[]
//    {
//        new TableRow(true, 1, DayLightType.NIGHT, CreatureType.Bat)
//    };

    public class TableRow {

        public boolean isLum;
        public int wind;
        public DayLightType daylight;
        public CreatureType creatureType;
        
        public TableRow(boolean isLum, int wind, DayLightType daylight,
                CreatureType creatureType) {
            
            this.isLum = isLum;
            this.wind = wind;
            this.daylight = daylight;
            this.creatureType = creatureType;
        }
    }
    IWeather weather;

    public Cloud(IWeather weather) {
        this.weather = weather;
    }

    public Creature getCreature() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
