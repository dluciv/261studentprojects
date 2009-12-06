/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class Pair {
    BigDecimal id;
    String name;

    public Pair(BigDecimal id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigDecimal getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    

}
