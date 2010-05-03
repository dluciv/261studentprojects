
package cloud;

public class Wind implements IWind {
    private int maximumWindForm = 11;

    public int generateWindForm() {
       return MyRandom.getRandom().nextInt(maximumWindForm);
    }
}
