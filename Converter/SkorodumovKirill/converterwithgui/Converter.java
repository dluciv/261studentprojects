//by Skorodumov K. gr: 261

package converterwithgui;

public class Converter 
{
	
	public static final String toHex(int x)
	{
		String res = "";
		int k = 0;
		int[] resInv = new int[10];
		while ((x/16) != 0)
		{
			resInv[k] = x % 16;
			x /= 16;
			k++;
		}
		resInv[k] = x;
		
		for(int i = k; i >= 0; i--)
		{
			if (resInv[i] < 10)
			{
				res +=resInv[i];
				continue;
			}
			if (resInv[i] == 10) 
			{
			 	res += 'A';
			 	continue;
			}
			if (resInv[i] == 11)
			{
				res += 'B';
				continue;
			}
			if (resInv[i] == 12)
			{
				res += 'C';
				continue;
			}
			if (resInv[i] == 13)
			{
				res += 'D';
				continue;
			}
			if (resInv[i] == 14)
			{
				res +='E';
			}
			if (resInv[i] == 15)
			{
				res += 'F';
			}
		}
		
		return res;
	}

}
