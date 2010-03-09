/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

public class Cloud implements ICloud {

    private IMagic magic;
    private CreatureTable table = new CreatureTable();

    public Cloud(IMagic magic) {
        this.magic = magic;
    }

    private Creature internalCreate(IWeatherFactory weatherFactory) {
        return table.getCreatureFromTable(weatherFactory);
    }

    public Creature create(IWeatherFactory weatherFactory) {
        Creature creature = internalCreate(weatherFactory);
        Courier courier = table.getCourierFromTable(creature);

        if (courier == Courier.Daemon) {
            magic.callDaemon();
        } else {
            magic.callStork();
        }

        magic.giveBaby(creature);

        return creature;
    }
}
