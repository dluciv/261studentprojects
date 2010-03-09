package interface_realization;

import exception_checking.IPhone;

public class StatPhone implements IPhone {
	
	public StatPhone(){		
	}
	
	public boolean isAvailable(){
		return true;
	}
	
	public boolean ring(){
		System.out.println("Stationary phone is ringing");
		return true;
	}

}
