/*
    (c) Stefan Bojarovski 2009
*/
package interface_realization;

public class MobilePhone implements IPhone {
    public boolean available;
	
    public MobilePhone(){
    }
    public boolean isAvailable(){
        return available;
    }
    public void ring(){
        System.out.println("Mobile is ringing");
    }
}
