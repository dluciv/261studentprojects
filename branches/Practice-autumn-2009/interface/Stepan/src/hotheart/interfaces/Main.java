/*
 * Part of Interfaces home tast by Korshakov Stepan
 * 261 Group
 */

package hotheart.interfaces;

import hotheart.interfaces.objects.IAreaObject;
import hotheart.interfaces.objects.Sphere;

/**
  * @author Korshakov Stepan
 */
public class Main {


    public static float calcArea(IAreaObject area)
    {
       if (area == null)
           throw new IllegalArgumentException();

       return area.calcArea();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sphere sphere = new Sphere(10.0f);
        try
        {
            calcArea(sphere);
        }
        catch(IllegalArgumentException e)
        {
            System.out.print("Wrong argument!");
        }
    }
}
