package database.index;

import tree.IndexableData;
import tree.UsableData;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey
 * Date: 28.08.2009
 * Time: 14:01:00
 * To change this template use File | Settings | File Templates.
 */
public class AddressData implements UsableData{
    long address;

    public AddressData(long address) {
        this.address = address;
    }

    public long getAddress() {
        return address;
    }
}
