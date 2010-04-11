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

package lexer;

public class TableCell {
    private int id;
    private String varName;
    private int varValue;

    TableCell(int id, String varName) {
        this.id = id;
        this.varName = varName;
    }

    public int getId() {
        return id;
    }

    public String getVarName() {
        return varName;
    }

    public int getVarValue() {
        return varValue;
    }

    public void setVarValue(int varValue) {
        this.varValue = varValue;
    }
}
