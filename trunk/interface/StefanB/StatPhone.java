package interface_realization;

import exception_checking.IPhone;

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
