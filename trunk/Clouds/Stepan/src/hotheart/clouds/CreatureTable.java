/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.clouds;

/**
 *
 * @author HotHeart
 */
public class CreatureTable {

    public static class CreatureTableRow {

        public boolean isShinig;
        public int windStart;
        public int windEnd;
        public DayLightType daylight;
        public CreatureType creature;
        public BabyCarrierType carrier;

        public CreatureTableRow(boolean isShinig, int windStart, int windEnd,
                DayLightType daylight, CreatureType creature,
                BabyCarrierType carrier) {
            this.isShinig = isShinig;
            this.windStart = windStart;
            this.windEnd = windEnd;
            this.daylight = daylight;
            this.creature = creature;
            this.carrier = carrier;
        }
    }
    
    public static CreatureTableRow[] Table = new CreatureTableRow[]{
            new CreatureTableRow(true, 0, 8, DayLightType.MORNING, CreatureType.Puppy, BabyCarrierType.Daemon),
            new CreatureTableRow(true, 9, 10, DayLightType.MORNING, CreatureType.Kitten, BabyCarrierType.Daemon),
            
            new CreatureTableRow(true, 0, 5, DayLightType.NOON, CreatureType.Hedgehog, BabyCarrierType.Storck),
            new CreatureTableRow(true, 6, 7, DayLightType.NOON, CreatureType.Bearcub, BabyCarrierType.Storck),
            new CreatureTableRow(true, 8, 9, DayLightType.NOON, CreatureType.Piglet, BabyCarrierType.Daemon),
            new CreatureTableRow(true, 10, 10, DayLightType.NOON, CreatureType.Bat, BabyCarrierType.Storck),
            
            new CreatureTableRow(true, 0, 7, DayLightType.EVENING, CreatureType.Puppy, BabyCarrierType.Storck),
            new CreatureTableRow(true, 8, 10, DayLightType.EVENING, CreatureType.Kitten, BabyCarrierType.Daemon),
            
            new CreatureTableRow(true, 0, 3, DayLightType.NIGHT, CreatureType.Piglet, BabyCarrierType.Daemon),
            new CreatureTableRow(true, 4, 6, DayLightType.NIGHT, CreatureType.Hedgehog, BabyCarrierType.Daemon),
            new CreatureTableRow(true, 7, 10, DayLightType.NIGHT, CreatureType.Balloon, BabyCarrierType.Storck),

            new CreatureTableRow(false, 0, 4, DayLightType.MORNING, CreatureType.Puppy, BabyCarrierType.Daemon),
            new CreatureTableRow(false, 5, 8, DayLightType.MORNING, CreatureType.Kitten, BabyCarrierType.Storck),
            new CreatureTableRow(false, 9, 10, DayLightType.MORNING, CreatureType.Bat, BabyCarrierType.Daemon),

            new CreatureTableRow(false, 0, 5, DayLightType.NOON, CreatureType.Kitten, BabyCarrierType.Storck),
            new CreatureTableRow(false, 6, 8, DayLightType.NOON, CreatureType.Bearcub, BabyCarrierType.Storck),
            new CreatureTableRow(false, 9, 10, DayLightType.NOON, CreatureType.Balloon, BabyCarrierType.Daemon),

            new CreatureTableRow(false, 0, 1, DayLightType.EVENING, CreatureType.Kitten, BabyCarrierType.Daemon),
            new CreatureTableRow(false, 2, 8, DayLightType.EVENING, CreatureType.Bearcub, BabyCarrierType.Daemon),
            new CreatureTableRow(false, 9, 10, DayLightType.EVENING, CreatureType.Balloon, BabyCarrierType.Storck),

            new CreatureTableRow(false, 0, 5, DayLightType.NIGHT, CreatureType.Hedgehog, BabyCarrierType.Storck),
            new CreatureTableRow(false, 6, 8, DayLightType.NIGHT, CreatureType.Bat, BabyCarrierType.Daemon)
        };
}
