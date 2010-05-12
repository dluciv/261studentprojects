/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yaskov;

public class EnvironmentCell {
    private int id;
    private Object value; // может быть как целым числом, так и логическим операндом;

    public EnvironmentCell(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
