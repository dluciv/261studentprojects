package clouds;

public interface IWeatherFactory 
{
	public IDaylight createDaylight();
	public IWind createWind();
	public ILuminary createLuminary();
}
