package Database;

public class SimpleTimer {
	private long _start;
	
	public SimpleTimer() {
		_start = System.currentTimeMillis();
	}
	
	public double getRemainder() {
		return (double)(System.currentTimeMillis() - _start) / 1000;
	}
	public void out() {
		System.out.println(getRemainder());
	}
}