package msavenko;

public interface IWeatherFactory {

	public IDaylight CreateDaylight();
	
	public IWind CreateWind();
	
	public ILuminary CreateLuminary();
}
