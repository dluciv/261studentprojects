/*
    (c) Stefan Bojarovski 2009
*/
package exception_checking;

public class MobilePhone implements IPhone {
	
    public boolean available;
	
    public MobilePhone(){
    }
    
    public void ring(){
        System.out.println("Mobile is ringing");
    }
	
    public boolean isAvailable() {
        return available;
    }
}
