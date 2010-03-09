/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package clouds;

public class Cloud implements ICloud {
    CreatureGenerator generator = new CreatureGenerator();
    IMagic magic = new Magic();

    public Cloud(IMagic magic) {
        this.magic = magic;
    }

    public Creature Create(IWeather weather) {
        Creature creature = generator.generateCreature(weather);
        Deliveryman deliverer = generator.generateDeliveryman(creature);

        if (deliverer.deliverymanType == DelivererType.DAEMON) {
            magic.callDaemon();
        }
        else {
            magic.callStork();
        }

        magic.giveBaby();
        
        return creature;
    }
}