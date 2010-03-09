/*
 * Cloud class by Korshakov Stepan
 * Group 261 - 2009(c)
 */
package hotheart.clouds;

/**
 * @author Korshakov Stepan
 */
public class Cloud {

    IWeather weather;
    IMagic magic;

    public Cloud(IWeather weather, IMagic magic) {
        this.weather = weather;
        this.magic = magic;
    }

    public BabyCarrier getCreature() {

        DayLightType daylight = weather.getDayLight().getDayLightType();
        boolean isShining = weather.getLuminary().isShining();
        int windSpeed = weather.getWind().getSpeed();

        Creature result = null;
        BabyCarrier carrier = null;

        for (CreatureTable.CreatureTableRow row : CreatureTable.Table) {
            if (row.testParameters(isShining, windSpeed, daylight)) {
                result = new Creature(row.creature);
                if (row.carrier == BabyCarrierType.Daemon) {
                    carrier = magic.CallDaemon();
                } else {
                    carrier = magic.CallStork();
                }
                break;
            }
        }

        carrier.giveBaby(result);
        return carrier;
    }
}
