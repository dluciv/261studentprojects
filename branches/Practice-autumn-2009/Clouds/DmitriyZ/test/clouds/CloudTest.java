/*
 * Clouds
 * Cloud Test
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(JMock.class)
public class CloudTest {

    Mockery context = new JUnit4Mockery();
    IMagic magic = new Magic();

    @Test
    public void BatCreate() {
        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.NOTSHINING, DaylightEnum.NIGHT, 4);
        ICloud cloud = new Cloud(magic, factory);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnum.Bat);
    }

    @Test
    public void PigletCreate() {
        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.NOTSHINING, DaylightEnum.NOON, 3);
        ICloud cloud = new Cloud(magic, factory);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnum.Piglet);
    }

    @Test
    public void BalloonCreate() {
        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.NOTSHINING, DaylightEnum.MORNING, 0);
        ICloud cloud = new Cloud(magic, factory);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnum.Balloon);
    }

    @Test
    public void PuppyCreate() {
        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.ISSHINING, DaylightEnum.EVENING, 6);
        ICloud cloud = new Cloud(magic, factory);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnum.Puppy);
    }

    @Test
    public void KittenCreate() {
        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.ISSHINING, DaylightEnum.EVENING, 4);
        ICloud cloud = new Cloud(magic, factory);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnum.Kitten);
    }

    @Test
    public void HedgehogCreate() {
        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.ISSHINING, DaylightEnum.NOON, 10);
        ICloud cloud = new Cloud(magic, factory);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnum.Hedgehog);
    }

    @Test
    public void BearcubCreate() {
        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.ISSHINING, DaylightEnum.NOON, 7);
        ICloud cloud = new Cloud(magic, factory);
        Creature creature = cloud.create();
        assertEquals(creature.getType(), CreatureEnum.Bearcub);
    }

    @Test
    public void testCallStork() {

        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.ISSHINING, DaylightEnum.NOON, 10);
        final IMagic mmagic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(mmagic, factory);

        context.checking(new Expectations() {

            {
                oneOf(mmagic).callStork();
                oneOf(mmagic).giveBaby(CreatureEnum.Hedgehog);
            }
        });

        cloud.create();
    }

    @Test
    public void testCallDaemon() {

        IWeatherFactory factory = new MockWeatherFactory(LuminaryEnum.ISSHINING, DaylightEnum.NOON, 7);
        final IMagic mmagic = context.mock(IMagic.class);
        ICloud cloud = new Cloud(mmagic, factory);
        context.checking(new Expectations() {

            {
                oneOf(mmagic).callDaemon();
                oneOf(mmagic).giveBaby(CreatureEnum.Bearcub);
            }
        });

        cloud.create();
    }
}