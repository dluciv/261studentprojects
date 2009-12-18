package exception_checking;

import exception_checking.IPhone;

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
