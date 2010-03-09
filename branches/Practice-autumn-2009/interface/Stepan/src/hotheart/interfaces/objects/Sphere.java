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

    private float radius;

    public Sphere(float Radius) {
        this.radius = Radius;
    }

    public float calcVolume() {
        return (float) Math.PI * radius * radius * radius * 4 / 3;
    }

    public float calcArea() {
        return (float) Math.PI * radius * radius * 4;
    }
}
