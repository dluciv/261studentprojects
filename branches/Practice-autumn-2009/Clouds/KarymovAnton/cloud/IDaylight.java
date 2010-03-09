
package cloud;

public interface IDaylight {
    public enum DaylightType { Day, Noon, Evening, Night}

    public DaylightType generateDaylightType();
}
