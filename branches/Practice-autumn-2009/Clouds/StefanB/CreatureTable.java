/*
 * (c) Stefan Bojarovski 2010
 * */
package cloud;

public class CreatureTable {
	
	public static class CreatureTableRow{
		
		public boolean isShinig;
        public int windStart;
        public int windEnd;
        public DaylightEnumType daylight;
        public CreatureEnumType creature;
        public CarrierEnumType carrier;
        
        public CreatureTableRow(int windStart, int windEnd, boolean isShinig,
                				DaylightEnumType daylight, CreatureEnumType creature,
                				CarrierEnumType carrier) {
            this.isShinig = isShinig;
            this.windStart = windStart;
            this.windEnd = windEnd;
            this.daylight = daylight;
            this.creature = creature;
            this.carrier = carrier;
        }
	}
	
	public static CreatureTableRow[] Table = new CreatureTableRow[]{

		new CreatureTableRow(0, 5, true, DaylightEnumType.Morning, CreatureEnumType.Puppy, CarrierEnumType.Stork),
		new CreatureTableRow(0, 5, true, DaylightEnumType.Noon, CreatureEnumType.Kitten, CarrierEnumType.Stork),
		new CreatureTableRow(0, 5, true, DaylightEnumType.Evening, CreatureEnumType.Hedgehog, CarrierEnumType.Deamon),
		new CreatureTableRow(0, 5, true, DaylightEnumType.Night, CreatureEnumType.Bearcub, CarrierEnumType.Deamon),
		
		new CreatureTableRow(0, 5, false, DaylightEnumType.Morning, CreatureEnumType.Piglet, CarrierEnumType.Stork),
		new CreatureTableRow(0, 5, false, DaylightEnumType.Noon, CreatureEnumType.Bat, CarrierEnumType.Deamon),
		new CreatureTableRow(0, 5, false, DaylightEnumType.Evening, CreatureEnumType.Balloon, CarrierEnumType.Stork),
		new CreatureTableRow(0, 5, false, DaylightEnumType.Night, CreatureEnumType.Puppy, CarrierEnumType.Stork),
		
		new CreatureTableRow(6, 10, true, DaylightEnumType.Morning, CreatureEnumType.Kitten, CarrierEnumType.Stork),
		new CreatureTableRow(6, 10, true, DaylightEnumType.Noon, CreatureEnumType.Hedgehog, CarrierEnumType.Deamon),
		new CreatureTableRow(6, 10, true, DaylightEnumType.Evening, CreatureEnumType.Bearcub, CarrierEnumType.Deamon),
		new CreatureTableRow(6, 10, true, DaylightEnumType.Night, CreatureEnumType.Piglet, CarrierEnumType.Stork),
		
		new CreatureTableRow(6, 10, false, DaylightEnumType.Morning, CreatureEnumType.Bat, CarrierEnumType.Deamon),
		new CreatureTableRow(6, 10, false, DaylightEnumType.Noon, CreatureEnumType.Balloon, CarrierEnumType.Stork),
		new CreatureTableRow(6, 10, false, DaylightEnumType.Evening, CreatureEnumType.Puppy, CarrierEnumType.Stork),
		new CreatureTableRow(6, 10, false, DaylightEnumType.Night, CreatureEnumType.Kitten, CarrierEnumType.Stork),
	};
}
