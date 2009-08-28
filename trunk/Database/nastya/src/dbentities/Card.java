package dbentities;

import utils.Util;
import tree.IndexableData;
import tree.Key;
import tree.UsableData;
import database.index.DatabaseKey;
import database.index.AddressData;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
 * Date: 20.08.2009
 * Time: 20:13:17
 * To change this template use File | Settings | File Templates.
 */
public class Card implements IndexableData {
    private String name;
    private String lastName;
    private String middleName;
    private Sex sex;
    private String phone;
    private String address;
    private long filePosition;

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

//    public int compareTo(Object o) {
//        if (o == null) return -1;
//        if (o.getClass() != getClass()) return -1;
//        Card card = (Card) o;
//        int lastNameCompare = Util.compare(lastName, card.lastName);
//        int nameCompare = Util.compare(name, card.name);
//        int middleNameCompare = Util.compare(middleName, card.middleName);
//        int phoneCompare = Util.compare(phone, card.phone);
//        if(lastNameCompare != 0){
//            return lastNameCompare;
//        }else if(nameCompare != 0){
//            return nameCompare;
//        }else if(middleNameCompare != 0){
//            return middleNameCompare;
//        }else{
//            return phoneCompare;
//        }

    //    }
    public Key extractKey() {
        return new DatabaseKey(lastName, name, middleName, null);
    }

    public UsableData extractUsableData() {
        return new AddressData(filePosition);
    }

    public void setFilePosition(long filePosition) {
        this.filePosition = filePosition;
    }
}
