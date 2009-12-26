//(с) Кривых Алексей 2009г.
//Cloud
package mult;

public class Creature {

    CreatureType type;

    //Конструктор класса Creature
    public Creature(CreatureType input) {
        type = input;
    }

    //Возвращает тип существа
    public CreatureType getType() {
        return type;
    }
}
