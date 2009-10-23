/**
 * Mass converter
 * (c)Pasha_Zolnikov, 2009
 */



public class MassConverter implements Converter{

    //килограмм -> фунт
    public double convertForwards(double kilogramms) {
        return 2.2 * kilogramm;
    }

    //фунт -> килограмм
    public double convertBackwards(double pounds) {
        return pound / 2.2;
    }

}
