package database.index;

import tree.BPlusTree;
import dbentities.Card;

import java.io.File;
import java.util.Vector;

import database.parser.DatabaseParser;
import database.parser.ParserException;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey
 * Date: 28.08.2009
 * Time: 14:10:56
 * To change this template use File | Settings | File Templates.
 */
public class IndexBuilder {

    public static BPlusTree<Card> generateIndex(String filename, int capacity) throws ParserException {
        DatabaseParser parser = new DatabaseParser();
        BPlusTree<Card> tree = new BPlusTree<Card>(capacity);
        Vector<Card> data = parser.parse(filename);
        for (Card card : data) {
            tree.add(card);
        }
        return tree;
    }
}
