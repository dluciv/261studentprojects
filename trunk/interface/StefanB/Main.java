/*
    (c) Stefan Bojarovski
    Program that demonstrates the use of interfaces in java.
*/
package interface_realization;

public class Main {

    public static void main(String[] args) {
        
        //create objects from two different classes
        MobilePhone cell = new MobilePhone();
		StatPhone stat = new StatPhone();
        
        //both classes implement the same interface, 
        //we can call the same method
        cell.ring();
        stat.ring();
	}
}
