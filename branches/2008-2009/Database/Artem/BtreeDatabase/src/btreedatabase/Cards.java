package btreedatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class Cards{
    static int i = 0;
    static NameComparator nameComparator = new NameComparator();
    static Name2Comparator name2Comparator = new Name2Comparator();
    static Name3Comparator name3comparator = new Name3Comparator();
    static AddrComparator addrComparator = new AddrComparator();
    static PhoneComparator phoneComparator = new PhoneComparator();
    static ismaleComparator ismaleComparator = new ismaleComparator();

    static public ArrayList<String> GenerateNames()
    {
        ArrayList<String> names = new ArrayList<String>();
        names.add(new String("Carlos"));
        names.add(new String("Fedor"));
        names.add(new String("Andersen"));
        names.add(new String("Bolita"));
        names.add(new String("Lolo"));
        names.add(new String("Monica"));
        names.add(new String("Olga"));
        names.add(new String("Hue"));
        names.add(new String("Hornblower"));
        names.add(new String("Horatio"));
        names.add(new String("Swety"));
        names.add(new String("Bob"));
        return names;
    }

    public static class NameComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            CardItem o1 = (CardItem)obj1;
            CardItem o2 = (CardItem)obj2;
            int res = (int)(o1).getName().compareTo((o2).getName());
            return res;
        }
    }

    public static class Name2Comparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            CardItem o1 = (CardItem)obj1;
            CardItem o2 = (CardItem)obj2;
            return (int)(o1).getName2().compareTo((o2).getName2());
        }
    }

    public static class Name3Comparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            CardItem o1 = (CardItem)obj1;
            CardItem o2 = (CardItem)obj2;
            return (int)((CardItem)o1).getName3().compareTo(((CardItem)o2).getName3());
        }
    }

    public static class AddrComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            CardItem o1 = (CardItem)obj1;
            CardItem o2 = (CardItem)obj2;
            return (int)((CardItem)o1).getAddr().compareTo(((CardItem)o2).getAddr());
        }
    }

    public static class PhoneComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            CardItem o1 = (CardItem)obj1;
            CardItem o2 = (CardItem)obj2;
            return (int)((CardItem)o1).getPhone().compareTo(((CardItem)o2).getPhone());
        }
    }

    public static class ismaleComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            CardItem obj1 = (CardItem)o1;
            CardItem obj2 = (CardItem)o2;
            if(obj1.isSex() == true && obj2.isSex() == false){
                return 1;
            } else if (obj2.isSex() == true && obj1.isSex() == false){
                return -1;
            }
            return 0;
        }
    }

    public List sort(List card_set, Comparator comp) throws Exception{
        ArrayList res = new ArrayList(card_set.size());
        res.addAll(card_set);
        Collections.sort(res, comp);
        return res;
    }

    class CardSortException extends Exception {}

    static private int random_index(int max)
    {
        int obj = (int) Double.doubleToLongBits(Math.random());
        int res =  Math.abs(obj) % max;
        return res;
    }

    static public CardItem GenerateRandomCard()
    {
        try {
        String tmp = String.valueOf(i++);
        ArrayList<String> names = GenerateNames();
        boolean sex;
        if (((int) Double.doubleToLongBits(Math.random()) % 1) == 0) {
            sex = true;
        } else {
            sex = false;
        }       
            return new CardItem(tmp, names.get(random_index(names.size())), names.get(random_index(names.size())), names.get(random_index(names.size())), names.get(random_index(names.size())), sex);
        } catch (CardItem.CreateCardException ex) {
            Logger.getLogger(Cards.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
