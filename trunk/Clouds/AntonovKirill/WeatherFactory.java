/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clouds;


public class WeatherFactory implements IWeatherFactory {

	@Override
	public IDaylight CreateDaylight() {
		IDaylight daylight = new Daylight();
		return daylight;
	}

	@Override
	public ILuminary CreateLuminary() {
		ILuminary luminary = new Luminary();
		return luminary;
	}

	@Override
	public IWind CreateWind() {
		IWind wind = new Wind();
		return wind;
	}
}