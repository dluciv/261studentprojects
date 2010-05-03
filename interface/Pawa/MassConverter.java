/**
 * Mass converter
 * (c)Pasha_Zolnikov, 2009
 */



public class MassConverter implements Converter{
	public double poundsInKilogramm;
	
    //килограмм -> фунт
    public double convertForwards(double kilogramms) {
        return poundsInKilogramm * kilogramm;
    }

    //фунт -> килограмм
    public double convertBackwards(double pounds) {
        return pound / poundsInKilogramm;
    }

}
