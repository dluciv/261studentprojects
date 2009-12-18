package interface_realization;

import exception_checking.IPhone;

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
