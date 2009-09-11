/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.dfa;

import java.util.Vector;

/**
 *
 * @author nastya
 */
public class VectorWithIndiscernibleOrder<E> extends Vector<E> {

    @Override
    public synchronized boolean equals(Object o) {
        if (o == null) {
            return false;
        }
//        if (!o.getClass().equals(getClass())) {
//            return false;
//        }

        Vector o1 = (Vector) o;
        Vector n = (Vector) o1.clone();
        for (int i = 0; i < size(); i++) {
            n.remove(get(i));
        }

        if (n.isEmpty() && (o1.size() == size())) {
            return true;
        }
        return false;
    }
}
