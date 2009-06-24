/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package btreedatabase;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String fn = new String("btree_dbs.index");
            DatabaseBtree<CardItem> database = new DatabaseBtree<CardItem>(Cards.GenerateRandomCard());
            database.InitDataFile(fn, 10);
            CardItem cardSample1 = new CardItem(String.valueOf(3), String.valueOf(2), String.valueOf(-2), String.valueOf(-2), String.valueOf(-2), true);
            CardItem cardSample2 = new CardItem(String.valueOf(4), String.valueOf(2), String.valueOf(-2), String.valueOf(-2), String.valueOf(-2), true);

            System.out.println(database.ReadIndex(database.MakeIndex(cardSample1, cardSample2, Cards.nameComparator)));

            System.out.println(database.ReadIndex(database.SimpleFindInDataFile(cardSample1, cardSample2, Cards.nameComparator)));

            System.out.println(database.ReadIndex(database.FindMin(cardSample1, cardSample2, Cards.nameComparator)));

            System.out.println(database.ReadIndex(database.SimpleFindMinInDataFile(cardSample1, cardSample2, Cards.nameComparator)));
        } catch (CardItem.CreateCardException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
