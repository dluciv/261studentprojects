package database.search;

import tree.Key;
import tree.BPlusTree;
import tree.TreeLeaf;
import tree.IndexableData;
import dbentities.Condition;
import dbentities.Card;
import database.index.AddressData;
import database.index.DatabaseKey;

import java.util.Vector;

/**
 * Итератор по базе данных
 *
 * @author nastya
 *         Date: 30.08.2009
 *         Time: 3:9:17
 */
public class DatabaseIterator {
    // текущий ключ
    private DatabaseKey currentKey;
    //если при сравнении с этим условием возвращается положительное число, то
    // итератор прекращает проход
    private Condition stopCondition;
    private BPlusTree<Card, DatabaseKey,AddressData> tree;

    public DatabaseIterator(Condition startCondition, Condition stopCondition, BPlusTree<Card, DatabaseKey, AddressData> tree) {
        this.stopCondition = stopCondition;
        this.tree = tree;
        this.currentKey = tree.find(new DatabaseKey(startCondition));
    }

    /**
     * Есть ли еще элементы, которые может вернуть итератор
     * @return <code>true</code>, если в итеаторе еще  есть элементы; <code>false</code> иначе
     */
    public boolean hasNext(){
        return currentKey != null;
    }


    /**
     * Берет следующий ключ дерева на нижнем уровне, если ключи еще есть и
     * условие останова не дстигнуто и возвращает данные из листа, прикрепленного к нему
     * @return
     */
    public Vector<AddressData> next(){
        Key oldKey = currentKey;
        //Ошибка использования итератора
        if(oldKey == null || !(oldKey.getLink() instanceof TreeLeaf)){
            throw new IteratorException();
        }
        //Берем следующий ключ в дереве (в уровне)
        if(currentKey != null){
            currentKey = tree.keyAfter(currentKey);
            // если условие останова достигнуто, то итератор больше не возвращает ключей
            if(currentKey != null && currentKey.compareTo(new DatabaseKey(stopCondition)) > 0){
                currentKey = null;
            }
        }

        return ((TreeLeaf<AddressData>)oldKey.getLink()).getAllData();
    }


}
