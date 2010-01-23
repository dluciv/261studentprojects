/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clouds;

public interface IWeatherFactory {

	public IDaylight CreateDaylight();

	public IWind CreateWind();

	public ILuminary CreateLuminary();
}
