package database.index;

import tree.IndexableData;
import tree.UsableData;

/**
 * Данные, содержащие адрес записи. Эти данные используются как листья дерева
 *
 * @author Sergey
 * Date: 28.08.2009
 * Time: 14:01:00
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
