/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class Id implements Expression {

    private String id;

    public Id(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
