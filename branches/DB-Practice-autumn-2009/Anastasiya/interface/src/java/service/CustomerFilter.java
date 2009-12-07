/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

/**
 *
 * @author Admin
 */
public class CustomerFilter {
    public static final String MAINTANCER_ALL = "ALL";
    public static final String MAINTANCER_WITHOUT = "WITHOUT";
    private String maintancerId;


    public CustomerFilter() {
        maintancerId = MAINTANCER_ALL;
    }

    public String getMaintancerId() {
        return maintancerId;
    }

    public void setMaintancerId(String maintancerId) {
        this.maintancerId = maintancerId;
    }

    

}
