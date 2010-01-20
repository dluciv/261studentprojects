package converterwithgui;

public class Converter {
	
	public static final long toBinary(Integer x)
	{
		long res = 0;
		long k = 1;
		while ((x/2) != 0)
		{
			res += (x % 2)*k;
			x /= 2;
			k *= 10;
		}
		res += x*k;
		
		return res;
	}

}
