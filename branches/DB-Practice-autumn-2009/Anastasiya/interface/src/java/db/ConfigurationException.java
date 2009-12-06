/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

/**
 *
 * @author Admin
 */
public class ConfigurationException extends RuntimeException{

    ConfigurationException(Exception ex) {
        super(ex);
    }

}
