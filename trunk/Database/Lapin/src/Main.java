import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lapin Sergey
 */
public class Main {

    public static void main(String[] args) {
        try {
            String FileName = new String("C:\\Documents and Settings\\Lapa\\Мои документы\\NetBeansProjects\\B_Tree\\test\\smth.lsk");
            DatabaseManager<Card> database = new DatabaseManager<Card>(CardSet.GenerateRandomCard());
            database.InitDataFile(FileName, 100000000 / CardSet.GenerateRandomCard().getRecordSize());
            Card tmp1 = new Card(String.valueOf(3), String.valueOf(2), String.valueOf(-2), String.valueOf(-2), String.valueOf(-2), true);
            Card tmp2 = new Card(String.valueOf(4), String.valueOf(2), String.valueOf(-2), String.valueOf(-2), String.valueOf(-2), true);
            System.out.println(database.ReadIndex(database.MakeIndex(tmp1, tmp2, CardSet.name_comparator)));
            System.out.println(database.ReadIndex(database.SimpleFindInDataFile(tmp1, tmp2, CardSet.name_comparator)));
        } catch (Card.CreateCardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
