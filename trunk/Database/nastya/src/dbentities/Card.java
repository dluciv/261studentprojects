package dbentities;

import utils.Util;
import tree.IndexableData;
import tree.Key;
import tree.UsableData;
import database.index.DatabaseKey;
import database.index.AddressData;

/**
 * Представляет собой запись в базе данных. Эта запись может добавляться в дерево
 * @author nastya
 * Date: 20.08.2009
 * Time: 20:13:17
 *
 * @see tree.BPlusTree
 * @see tree.Key
 * @see tree.IndexableData
 * @see tree.UsableData
 * @see database.index.DatabaseKey
 * @see database.index.AddressData
 *
 */
public class Card implements IndexableData<DatabaseKey, AddressData> {
    //Поля базы
    private String name;
    private String lastName;
    private String middleName;
    private Sex sex;
    private String phone;
    private String address;
    // позиция начала записи в файле. Хранится для индексирования
    private long filePosition;
    // Длина записи в байтах. Необходима при чтении для определения
    // позиции следующих записей в файле
    private long byteLength;

    public Card(String name, String lastName, String middleName, Sex sex,
                String phone, String address) {
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setFilePosition(long filePosition) {
        this.filePosition = filePosition;
    }

    public void setByteLength(long byteLength) {
        this.byteLength = byteLength;
    }

    public long getByteLength() {
        return byteLength;
    }
    public DatabaseKey extractKey() {
        return new DatabaseKey(new Condition(lastName, name, middleName), null);
    }

    public AddressData extractUsableData() {
        return new AddressData(filePosition);
    }

}
