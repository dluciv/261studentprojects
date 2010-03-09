package converter;

import java.util.*;

public class ExchangeRateTable {
	private static HashMap<Pair<String, String>, Double> table = new HashMap<Pair<String, String>, Double>();
	
	public static double getExchangeRate(Currency in, Currency out){
		 Pair<String, String> key = new Pair<String, String>(in.getType(), out.getType());
		 return table.get(key);
	}
	
	public static void setExchangeRate(Currency first, Currency second, double rate){
		Pair<String, String> keyFirstToSecond = new Pair<String, String>(first.getType(), second.getType());
		Pair<String, String> keySecondToFirst = new Pair<String, String>(second.getType(), first.getType());
		
		table.put(keyFirstToSecond,rate);
		table.put(keySecondToFirst, (1 / rate));
	}

}
