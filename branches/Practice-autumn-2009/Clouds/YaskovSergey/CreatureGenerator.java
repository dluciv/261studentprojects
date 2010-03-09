/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package clouds;

public class CreatureGenerator {
    public Creature generateCreature(IWeather weather) {
        if (weather.getLuminary().getLuminaryType() == LuminaryType.ISSHINING) {
            switch (weather.getDaylight().getDaylightType()) {
                case MORNING:
                    if (weather.getWind().getWindPower() <= 5)
                        return new Creature(CreatureType.PIGLET);
                    else
                        return new Creature(CreatureType.KITTEN);

                case NOON:
                    if (weather.getWind().getWindPower() <= 5)
                        return new Creature(CreatureType.BALLOON);
                    else
                        return new Creature(CreatureType.PUPPY);

                case DAY:
                    if (weather.getWind().getWindPower() <= 5)
                        return new Creature(CreatureType.BAT);
                    else
                        return new Creature(CreatureType.PUPPY);

                case NIGHT:
                    if (weather.getWind().getWindPower() <= 5)
                        return new Creature(CreatureType.BEARCUB);
                    else
                        return new Creature(CreatureType.HEDGEHOG);
            }
        }
        else {
            switch (weather.getDaylight().getDaylightType()) {
                case MORNING:
                    if (weather.getWind().getWindPower() <= 5)
                        return new Creature(CreatureType.PUPPY);
                    else
                        return new Creature(CreatureType.BEARCUB);

                case NOON:
                    if (weather.getWind().getWindPower() <= 5)
                        return new Creature(CreatureType.BAT);
                    else
                        return new Creature(CreatureType.KITTEN);

                case DAY:
                    if (weather.getWind().getWindPower() <= 5)
                        return new Creature(CreatureType.HEDGEHOG);
                    else
                        return new Creature(CreatureType.BAT);

                case NIGHT:
                    if (weather.getWind().getWindPower() <= 5)
                        return new Creature(CreatureType.PIGLET);
                    else
                        return new Creature(CreatureType.BALLOON);
            }
        }

        return new Creature(CreatureType.BALLOON);
    }

    public Deliveryman generateDeliveryman(Creature creature) {
        switch (creature.creatureType) {
            case PIGLET:
                return new Deliveryman(DelivererType.DAEMON);

            case KITTEN:
                return new Deliveryman(DelivererType.STORK);

            case BAT:
                return new Deliveryman(DelivererType.STORK);

            case BALLOON:
                return new Deliveryman(DelivererType.STORK);

            case PUPPY:
                return new Deliveryman(DelivererType.DAEMON);

            case HEDGEHOG:
                return new Deliveryman(DelivererType.DAEMON);

            case BEARCUB:
                return new Deliveryman(DelivererType.STORK);

            default:
                return new Deliveryman(DelivererType.DAEMON);
        }
    }
}
