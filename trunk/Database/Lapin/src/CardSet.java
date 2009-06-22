import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CardSet{
    static int i = 3;
    static NameComparator name_comparator = new NameComparator();
    static SonameComparator soname_comparator = new SonameComparator();
    static ThirdNameComparator third_namecomparator = new ThirdNameComparator();
    static AddressComparator address_comparator = new AddressComparator();
    static PhoneComparator phone_comparator = new PhoneComparator();
    static SexComparator sex_comparator = new SexComparator();    

    private CardSet()
    {
    }    

    static public ArrayList<String> GenerateNames1()
    {
        ArrayList<String> names = new ArrayList<String>();
        names.add(new String("Carlos"));
        names.add(new String("Fedor"));
        names.add(new String("Andersen"));
        names.add(new String("Bolita"));
        names.add(new String("Lolo"));
        names.add(new String("Monica"));
        names.add(new String("@lferd0g"));
        names.add(new String("Hue"));
        names.add(new String("Hornblower"));
        names.add(new String("Horatio"));
        names.add(new String("Swety"));
        names.add(new String("Bob"));
        return names;
    }

    static public ArrayList<String> GenerateNames()
    {
        ArrayList<String> names = new ArrayList<String>();
        names.add(new String("Carlos"));
        names.add(new String("Fedor"));
        names.add(new String("Andersen"));
        names.add(new String("Bolita"));
        names.add(new String("Lolo"));
        names.add(new String("Monica"));
        names.add(new String("@lferd0g"));
        names.add(new String("Hue"));
        names.add(new String("Hornblower"));
        names.add(new String("Horatio"));
        names.add(new String("Swety"));
        names.add(new String("Bob"));
        return names;
    }

    public static class NameComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            Card o1 = (Card)obj1;
            Card o2 = (Card)obj2;
            int res = (int)(o1).getName().compareTo((o2).getName());
            return res;
        }
    }

    public static class SonameComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            Card o1 = (Card)obj1;
            Card o2 = (Card)obj2;
            return (int)(o1).getSoname().compareTo((o2).getSoname());
        }
    }

    public static class ThirdNameComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            Card o1 = (Card)obj1;
            Card o2 = (Card)obj2;
            return (int)((Card)o1).getThird_name().compareTo(((Card)o2).getThird_name());
        }
    }

    public static class AddressComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            Card o1 = (Card)obj1;
            Card o2 = (Card)obj2;
            return (int)((Card)o1).getAddress().compareTo(((Card)o2).getAddress());
        }
    }

    public static class PhoneComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            Card o1 = (Card)obj1;
            Card o2 = (Card)obj2;
            return (int)((Card)o1).getPhone().compareTo(((Card)o2).getPhone());
        }
    }

    public static class SexComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            Card obj1 = (Card)o1;
            Card obj2 = (Card)o2;
            if(obj1.isSex() == true && obj2.isSex() == false){
                return 1;
            } else if (obj2.isSex() == true && obj1.isSex() == false){
                return -1;
            }
            return 0;
        }
    }

    public List sort(List card_set, Comparator comp) throws CardSortException{
        ArrayList res = new ArrayList(card_set.size());
        res.addAll(card_set);
        Collections.sort(res, comp);
        return res;
    }

    class CardSortException extends Exception {
    }

    static private int random_index(int max)
    {
        int obj = (int) Double.doubleToLongBits(Math.random());
        int res =  Math.abs(obj) % max;
        return res;
    }

    static public Card GenerateRandomCard1()
    {
        try {
            ArrayList<String> names = GenerateNames();
            boolean sex;
            if (((int) Double.doubleToLongBits(Math.random()) % 1) == 0) {
                sex = true;
            } else {
                sex = false;
            }
            return new Card(names.get(random_index(names.size())),
                            names.get(random_index(names.size())),
                            names.get(random_index(names.size())),
                            names.get(random_index(names.size())),
                            names.get(random_index(names.size())), sex);
        } catch (Card.CreateCardException ex) {
            System.out.println("Bad name set");
            return null;
        }
    }

    static public Card GenerateRandomCard()
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
        
            return new Card(tmp, names.get(random_index(names.size())), names.get(random_index(names.size())), names.get(random_index(names.size())), names.get(random_index(names.size())), sex);
        } catch (Card.CreateCardException ex) {
            Logger.getLogger(CardSet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
