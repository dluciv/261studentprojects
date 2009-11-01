/*
 * Part of Interfaces home tast by Korshakov Stepan
 * 261 Group
 */
package hotheart.interfaces.objects;

/**
 *
 * @author Korshakov Stepan
 */
public class Cube implements IVolumeObject, IAreaObject {

    public float W, H, L;

    public Cube(float W, float H, float L) {
        this.W = W;
        this.H = H;
        this.L = L;
    }

    public float calcVolume() {
        return Math.abs(W)*Math.abs(H)*Math.abs(L);
    }

    public float calcArea() {
        return Math.abs(W*H*2) + Math.abs(W*L*2) + Math.abs(L*H*2);
    }
}