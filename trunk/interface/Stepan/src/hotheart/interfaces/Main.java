/*
 * Part of Interfaces home tast by Korshakov Stepan
 * 261 Group
 */

package hotheart.interfaces;

import hotheart.interfaces.objects.IAreaObject;

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
        
    }
}
