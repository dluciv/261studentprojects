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

    private float width, height, length;

    public Cube(float Width, float Height, float Length) {
        this.width = Width;
        this.height = Height;
        this.length = Length;
    }

    public float calcVolume() {
        return Math.abs(width)*Math.abs(height)*Math.abs(length);
    }

    public float calcArea() {
        return Math.abs(width*height*2) + Math.abs(width*length*2) + Math.abs(length*height*2);
    }
}