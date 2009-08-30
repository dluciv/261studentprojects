package database.index;

import tree.BPlusTree;
import dbentities.Card;

import java.io.File;
import java.util.Vector;

import database.parser.DatabaseParser;
import database.parser.ParserException;

/**
 * Предоставляет способ построения индекса на основе файла базы
 *
 * @author nastya
 * Date: 28.08.2009
 * Time: 14:10:56
 * @see tree.BPlusTree
 */
public class IndexBuilder {

    /**
     * Разбирает файл базы и строит индекс для него
     *
     * @param filename имя файла, где содержится база
     * @param capacity размер контейнера в узле дерева
     * @return дерево-индекс, построенное по данному файлу базы
     * @throws ParserException ошибка чтения карточек из базы
     */
    public static BPlusTree<Card, DatabaseKey, AddressData> generateIndex(String filename, int capacity) throws ParserException {
        DatabaseParser parser = new DatabaseParser(filename);
        BPlusTree<Card, DatabaseKey,AddressData> tree = new BPlusTree<Card, DatabaseKey,AddressData>(capacity);
        Vector<Card> data = parser.parse();
        for (Card card : data) {
            tree.add(card);
        }
        return tree;
    }
}
