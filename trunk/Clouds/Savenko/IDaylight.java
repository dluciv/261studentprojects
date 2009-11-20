/*
 * Interface for Daylight class
 * have list of daylight types
 * Savenko Maria ©2009
 */

package msavenko;

public interface IDaylight {
	
	public enum DaylightType {Morning, Noon, Evening, Night}

	public DaylightType getDaylight();
	
}
