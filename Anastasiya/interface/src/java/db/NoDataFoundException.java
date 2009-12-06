/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class NoDataFoundException extends Exception{

    public NoDataFoundException(String id) {
        super(id);
    }

}
