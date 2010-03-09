/*
 * (c) Stefan Bojarovski 2009
 * */
package cloud;

public interface IWeather {
	
	public ILuminary getLuminary();
	public IDaylight getDaylight();
	public IWind getWind();
}
