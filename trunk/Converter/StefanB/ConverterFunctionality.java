package converter;

public class ConverterFunctionality {
	
	final static double MKD_TO_RUB = 0.719575287;
	final static double RUB_TO_MKD = 1.38970865;
	
	public static double convertToRub (double inMkd){
		return (inMkd*MKD_TO_RUB);
	}
	public static double convertToMkd (double inRub){
		return (inRub*RUB_TO_MKD);
	}
}
