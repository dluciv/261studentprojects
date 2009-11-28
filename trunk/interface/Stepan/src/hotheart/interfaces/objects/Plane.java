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

    public float Width, Height;

    public Plane(float Width, float Height) {
        this.Width = Width;
        this.Height = Height;
    }

    public float calcVolume() {
        return 0.0f;
    }

    public float calcArea() {
        return Math.abs(Width*Height);
    }
}
