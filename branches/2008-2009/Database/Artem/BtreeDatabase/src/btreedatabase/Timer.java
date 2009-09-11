package btreedatabase;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
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