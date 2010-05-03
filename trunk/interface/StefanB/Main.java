package interface_realization;


public class Main {

	public static void main(String[] args) {
		MobilePhone cell = new MobilePhone();
		cell.ring();
		
		StatPhone stat = new StatPhone();
		stat.ring();
	}

}
