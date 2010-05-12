/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 * ячейка в таблице переменных, содержащая идентификатор переменной, ее имя и значение, которое будет полу-
 * чено и установлено после интерпретации;
 * 
 */

package lebedev;

public class TableCell {
    private int id;
    private String varName;

    public TableCell(int id, String varName) {
        this.id = id;
        this.varName = varName;
    }

    public int getId() {
        return id;
    }

    public String getVarName() {
        return varName;
    }
}
