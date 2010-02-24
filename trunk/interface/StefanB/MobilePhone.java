package interface_realization;

import exception_checking.IPhone;

public class MobilePhone implements IPhone {
	
	private static int MAX_LIFE = 10;
	private int batteryLife;
	
	public MobilePhone(int life){
		if (life > MAX_LIFE){
			this.batteryLife = 10;
		}
		else if ((life <= MAX_LIFE)||(life >= 0)) { 
			this.batteryLife = life;
		}
		else if (life < 0){
			throw new IllegalArgumentException("Illegal charge value");
		}
	}
	
	public void charge(){
		this.batteryLife = MAX_LIFE;
	}
	
	public int getBatteryLife(){
		return batteryLife;
	}
	
	public boolean isAvailable(){
		if (batteryLife >=1){
			return true;
		}else 
			return false;
	}
	
	public boolean ring(){
		if (this.isAvailable()){
			System.out.println("Mobile is ringing");
			batteryLife--;
			return true;
		}
		else {
			throw new IndexOutOfBoundsException("Battery is dead");			
		}
	}

}
