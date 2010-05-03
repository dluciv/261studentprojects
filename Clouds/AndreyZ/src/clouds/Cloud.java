/**
 * Cloud Class
 * @author Zubrilin Andrey
 * group 261 (c) 2009
 */

package clouds;

public class Cloud implements ICloud {

    private IMagic magic;
    private CreatureTable table = new CreatureTable();

    public Cloud(IMagic magic) {
        this.magic = magic;
    }

        public CreatureType callBaby(IWFactory wFactory) {
        CreatureType creature = generatePets(wFactory);
        Herald herald = table.callHerald(creature);

        if (herald == Herald.Stork)
            magic.callStork();
        else
            magic.callDaemon();

        magic.giveBaby(creature);

        return creature;
    }

    private CreatureType generatePets(IWFactory wFactory) {
        return table.callCreature(wFactory);
    }

}
