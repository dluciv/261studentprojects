/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

public class Luminary implements ILuminary {

    private LuminaryType luminaryType;

    public Luminary(LuminaryType luminaryType) {
        this.luminaryType = luminaryType;
    }

    public LuminaryType current() {
        return luminaryType;
    }
}
