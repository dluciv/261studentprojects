package cloud;
import org.jmock.Mockery;
import org.jmock.Expectations;
import junit.framework.*;


public class CloudTest extends TestCase{

    Mockery context = new Mockery();
    IMagic magic = new Magic();

    public void testPuppy() {
        IWeather weather = new DummyWeather(true, DaylightEnumType.Morning, 0);
        ICloud cloud = new Cloud(magic, weather);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnumType.Puppy);
    }
    public void testKitten() {
        IWeather weather = new DummyWeather(true, DaylightEnumType.Noon, 1);
        ICloud cloud = new Cloud(magic, weather);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnumType.Kitten);
    }
    public void testHedgehog() {
        IWeather weather = new DummyWeather(true, DaylightEnumType.Evening, 2);
        ICloud cloud = new Cloud(magic, weather);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnumType.Hedgehog);
    }
    public void testBearcub() {
        IWeather weather = new DummyWeather(true, DaylightEnumType.Night, 3);
        ICloud cloud = new Cloud(magic, weather);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnumType.Bearcub);
    }
    public void testPiglet() {
        IWeather weather = new DummyWeather(false, DaylightEnumType.Morning, 4);
        ICloud cloud = new Cloud(magic, weather);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnumType.Piglet);
    }
    public void testBat() {
        IWeather weather = new DummyWeather(false, DaylightEnumType.Morning, 6);
        ICloud cloud = new Cloud(magic, weather);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnumType.Bat);
    }
    public void testBalloon() {
        IWeather weather = new DummyWeather(false, DaylightEnumType.Noon, 7);
        ICloud cloud = new Cloud(magic, weather);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnumType.Balloon);
    }
    
    public void testCallDeamon() {

        IWeather weather = new DummyWeather(true, DaylightEnumType.Evening, 2);
        final IMagic mockMagic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(mockMagic, weather);

        context.checking(new Expectations() {

            {
                oneOf(mockMagic).callDeamon();
                oneOf(mockMagic).giveCreature(CreatureEnumType.Hedgehog);
            }
        });

        cloud.create();
    }

    
    public void testCallStork() {

        IWeather weather = new DummyWeather(true, DaylightEnumType.Morning, 0);
        final IMagic mockMagic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(mockMagic, weather);

        context.checking(new Expectations() {

            {
                oneOf(mockMagic).callStork();
                oneOf(mockMagic).giveCreature(CreatureEnumType.Puppy);
            }
        });

        cloud.create();
    }
}