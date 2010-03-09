/**
 * Luminary Class
 * @author Zubrilin Andrey
 * group 261 (c) 2009
 */

package clouds;

public class Luminary implements ILuminary {

    private LuminaryType luminaryType;

    public Luminary(LuminaryType luminaryType) {
        this.luminaryType = luminaryType;
    }

    public LuminaryType isShiny() {
        return luminaryType;
    }
}