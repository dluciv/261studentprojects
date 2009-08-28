package tree;

import java.util.Vector;

/**
 * ѕредставл€ет "лист" дерева, то есть самый нежний уровень, содержащий данные.
 *
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 3:57:24
 */
public class TreeLeaf implements TreeElement{
    // Ќабор даных, хран€щийс€ в узле. Ќеобходимо дл€ того, что бы можно было хранить
    // несколько записей с идентичными ключами
    private Vector<UsableData> data;

    public TreeLeaf(){
        data = new Vector<UsableData>();
    }

  /**
     * ƒобавл€ет новый набор данных дл€ хранени€ в текущий узел
     *
     * @param usableData хранимые данные
     */
    public void add(UsableData usableData){
        data.add(usableData);
    }
    @Override
    public String toString() {
        return "[TreeLeaf: " + data + "]\n";
    }
}
