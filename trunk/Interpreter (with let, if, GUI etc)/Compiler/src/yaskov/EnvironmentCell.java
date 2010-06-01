/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yaskov;

public class EnvironmentCell {

    private int id;
    private Value value; // может быть целым числом, логическим операндом или функцией;

    public EnvironmentCell(int id, Value value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setValue(Value value) { // а оно надо?
        this.value = value;
    }

    public Value getValue() {
        return value;
    }
}
