import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String FileName = new String("C:\\Documents and Settings\\Administrator\\" + "My Documents\\NetBeansProjects\\B_Tree\\test\\smth.lsk");
            File f = new File(FileName);
            RandomAccessFile fileStream = new RandomAccessFile(f, "rw");
            int filesize = 60;
            Card tmp;
            for (int i = 0; i < filesize; i++) {
                tmp = CardSet.GenerateRandomCard();
                fileStream.write(tmp.serialize());
            }
            DatabaseManager<Card> database = new DatabaseManager<Card>(FileName, CardSet.GenerateRandomCard());
            Card tmp1 = new Card(String.valueOf(0), String.valueOf(2),
                    String.valueOf(-2), String.valueOf(-2), String.valueOf(-2), true);
            Card tmp2 = new Card(String.valueOf(9), String.valueOf(2),
                    String.valueOf(-2), String.valueOf(-2), String.valueOf(-2), true);

            System.out.println(database.ReadIndex(database.MakeIndex(tmp1, tmp2, CardSet.name_comparator)));
        } catch (Card.CreateCardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
