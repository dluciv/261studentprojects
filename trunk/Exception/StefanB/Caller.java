/*
    (c) Stefan Bojarovski 2009
*/
package exception_checking;

public class Caller {
    
    //calls a phone
    public boolean call (IPhone phone){
        if (phone == null){
            throw new IllegalArgumentException();
        }
        else
            return phone.isAvailable();
    }
}