package interface_realization;

import org.junit.Test;
import junit.framework.*;

public class InterfaceTests extends TestCase {
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeBattery(){
		MobilePhone mob = new MobilePhone(-1);
		mob.getBatteryLife();
	}
	
	@Test
	public void testOutOfReach(){
		MobilePhone mob = new MobilePhone(0);
		assertFalse(mob.isAvailable());
	}
	
	public void testDeadPhone()throws IndexOutOfBoundsException {
		MobilePhone mob = new MobilePhone(0);
		try{
			mob.ring();
		} catch(IndexOutOfBoundsException e){			
		}		
	}
	
	public void testGetLife(){
		MobilePhone mob = new MobilePhone(2);
		assertEquals(2, mob.getBatteryLife());
	}
	
	public void testMobileCharge(){
		MobilePhone mob = new MobilePhone(2);
		mob.charge();
		assertEquals(10, mob.getBatteryLife());
	}
	
	public void testMobilePhoneAvailable(){
		MobilePhone mob = new MobilePhone(1);
		assertTrue(mob.isAvailable());
	}
	
	public void testMobilePhoneRing(){
		MobilePhone mob = new MobilePhone(1);
		assertTrue(mob.ring());
	}
	
	public void testStaticPhoneAvailable(){
		StatPhone stat = new StatPhone();
		assertTrue(stat.isAvailable());
	}
	
	public void testStaticPhoneRing(){
		StatPhone stat = new StatPhone();
		assertTrue(stat.ring());
	}
}
