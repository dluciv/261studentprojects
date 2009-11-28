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

    public float Width, Height, Length;

    public Cube(float Width, float Height, float Length) {
        this.Width = Width;
        this.Height = Height;
        this.Length = Length;
    }

    public float calcVolume() {
        return Math.abs(Width)*Math.abs(Height)*Math.abs(Length);
    }

    public float calcArea() {
        return Math.abs(Width*Height*2) + Math.abs(Width*Length*2) + Math.abs(Length*Height*2);
    }
}