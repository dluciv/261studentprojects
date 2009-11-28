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

    public float Radius;

    public Sphere(float Radius) {
        this.Radius = Radius;
    }

    public float calcVolume() {
        return (float) Math.PI * Radius * Radius * Radius * 4 / 3;
    }

    public float calcArea() {
        return (float) Math.PI * Radius * Radius * 4;
    }
}
