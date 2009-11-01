/*
 * Part of Interfaces home tast by Korshakov Stepan
 * 261 Group
 */
package hotheart.interfaces.objects;

/**
 *
 * @author Korshakov Stepan
 */
public class Sphere implements IVolumeObject, IAreaObject {

    public float R;

    public Sphere(float R) {
        this.R = R;
    }

    public float calcVolume() {
        return (float) Math.PI * R * R * R * 4 / 3;
    }

    public float calcArea() {
        return (float) Math.PI * R * R * 4;
    }
}
