/*
 * Part of Interfaces home tast by Korshakov Stepan
 * 261 Group
 */
package hotheart.interfaces.objects;

/**
 *
 * @author Korshakov Stepan
 */
public class Plane implements IVolumeObject, IAreaObject {

    public float W, H;

    public Plane(float W, float H) {
        this.W = W;
        this.H = H;
    }

    public float calcVolume() {
        return 0.0f;
    }

    public float calcArea() {
        return Math.abs(W*H);
    }
}
