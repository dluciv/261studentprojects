/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.graph;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class SafeIterator<M> implements Iterator<M>{
    
    private Vector<M> vect;
    private Vector<M> clone;
    int cursor = 0;

    public SafeIterator(Vector<M> v) {
        vect = v;
        clone = new Vector<M>();
        clone.setSize(vect.size());
        Collections.copy(clone, vect);
    }

    public boolean hasNext() {
        return cursor < clone.size();
    }

    public M next() {
        return clone.elementAt(cursor++);
    }

    public void remove() {
        cursor--;
        vect.remove(cursor);
        clone.remove(cursor);
    }

}
