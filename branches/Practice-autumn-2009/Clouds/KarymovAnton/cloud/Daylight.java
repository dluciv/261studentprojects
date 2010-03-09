package cloud;

public class Daylight implements IDaylight{
    DaylightType daylightNow = DaylightType.values()[MyRandom.getRandom().nextInt(DaylightType.values().length)];;
    public DaylightType generateDaylightType() {
      return daylightNow;
    }
 
}
