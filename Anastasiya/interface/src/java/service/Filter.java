/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class Filter {
    HashMap<Object, Boolean> filter = new  HashMap<Object, Boolean>();

    public Filter() {
    }

    public int size() {
        return filter.size();
    }

    public Boolean put(Object key, Boolean value) {
        return filter.put(key, value);
    }

    public Boolean get(Object key) {
        return filter.get(key);
    }



}
