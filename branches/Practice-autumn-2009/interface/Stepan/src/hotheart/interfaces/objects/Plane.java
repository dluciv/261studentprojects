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

    private float width, height;

    public Plane(float Width, float Height) {
        this.width = Width;
        this.height = Height;
    }

    public float calcVolume() {
        return 0.0f;
    }

    public float calcArea() {
        return Math.abs(width*height);
    }
}
