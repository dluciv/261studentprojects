/*
    (c) Stefan Bojarovski 2009
*/
package interface_realization;

public class StatPhone implements IPhone {
    public boolean available;
    
    public StatPhone(){        
    }
    public boolean isAvailable(){
        return available;
    }
    public void ring(){
        System.out.println("Stationary phone is ringing");
    }
}
