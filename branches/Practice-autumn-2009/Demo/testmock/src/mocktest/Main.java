/* 
 * Reflection in Java
 * Victor Polozov (c) 2009 
 */

package mocktest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
	      Customer customer = new Customer(new Shop());

	      try {
	    	  Method drinkMethod = Customer.class.getDeclaredMethod("drink");
	    	  drinkMethod.setAccessible(true);
	    	  drinkMethod.invoke(customer);	    	  
	      } 
	      catch (NoSuchMethodException ex) { }
	      catch (InvocationTargetException ex) { }
	      catch (IllegalAccessException ex) { }
	      
	      System.out.println(customer.getIsDrunk());
	}

}
