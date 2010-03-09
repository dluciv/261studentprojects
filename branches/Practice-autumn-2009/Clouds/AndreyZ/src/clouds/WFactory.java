/**
 * Factory Class
 * @author Zubrilin Andrey
 * group 261 (c) 2009
 */
package clouds;

import java.util.Random;

public class WFactory implements IWFactory {

    private Wind wind;
    private Luminary luminary;
    private Daylight daylight;

    

    public WFactory() {

        Random random = new Random();

        switch (random.nextInt(4)) {
            case 0:
                daylight = new Daylight(DaylightType.Noon);
                break;
            case 1:
                daylight = new Daylight(DaylightType.Day);
                break;
            case 2:
                daylight = new Daylight(DaylightType.Evening);
                break;
            default:
                daylight = new Daylight(DaylightType.Night);
        }

        wind = new Wind(random.nextInt(11));
        if (random.nextBoolean())
            luminary = new Luminary(LuminaryType.Shiny);
        else
            luminary = new Luminary(LuminaryType.NotShiny);
    }

    public IDaylight daylight() {
        return daylight;
    }

    public ILuminary luminary() {
        return luminary;
    }

    public IWind wind() {
        return wind;
    }
}
