package converter;

public class CurrencyConverter {
	
	public CurrencyConverter(){
		
	}
	
	public double convert(Currency inCur, Currency outCur){
		double rate = ExchangeRateTable.getExchangeRate(inCur, outCur);
		double value = inCur.getAmount();
		return value*rate;		
	}
}
