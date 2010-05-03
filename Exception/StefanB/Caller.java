package exception_checking;


public class Caller {
	public boolean call (IPhone phone){
		if (phone == null){
			throw new IllegalArgumentException();
		}
		else
			return phone.isAvailable();
	}
}