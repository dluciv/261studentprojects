package btreedatabase;

/**
 *
 * @author Lapin Sergey
 */
public class Timer 
{
	private long _start;

	public Timer() {
		_start = System.currentTimeMillis();
	}

	public double getRemainder() {
		return (double)(System.currentTimeMillis() - _start) / 1000;
	}
	public void out() {
		System.out.println(getRemainder());
	}
}