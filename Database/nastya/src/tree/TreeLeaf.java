package tree;

import database.index.AddressData;

import java.util.Vector;

/**
 * Представляет "лист" дерева, то есть самый нежний уровень, содержащий данные.
 *
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 3:57:24
 */
public class TreeLeaf<U extends UsableData> implements TreeElement{
    // Набор даных, хранящийся в узле. Необходимо для того, что бы можно было хранить
    // несколько записей с идентичными ключами
    private Vector<U> data;

    public TreeLeaf(){
        data = new Vector<U>();
    }

    /**
       * Добавляет новый набор данных для хранения в текущий узел
       *
       * @param usableData хранимые данные
       */
    public void add(U usableData){
        data.add(usableData);
    }
    @Override
    public String toString() {
        return "[TreeLeaf: " + data + "]\n";
    }

    public Vector<U> getAllData() {
        return data;
    }
}
