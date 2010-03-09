package exception_checking;

import org.junit.Test;
import junit.framework.*;

public class InterfaceTests extends TestCase {
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeBattery(){
		MobilePhone mob = new MobilePhone(-1);
		mob.getBatteryLife();
	}
	
	
	public void testDeadPhone()throws IndexOutOfBoundsException {
		MobilePhone mob = new MobilePhone(0);
		try{
			mob.ring();
		} catch(IndexOutOfBoundsException e){			
		}		
	}
}
	