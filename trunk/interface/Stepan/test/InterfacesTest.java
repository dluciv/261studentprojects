/*
 * Tests for Interfaces by Stepan Korshakov
 */

import hotheart.interfaces.Main;
import hotheart.interfaces.objects.Cube;
import hotheart.interfaces.objects.Plane;
import hotheart.interfaces.objects.Sphere;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Korshakov Stepan
 */
public class InterfacesTest {

    @Test
    public void CubeTest() 
    {
        Cube cube = new Cube(10,12,13);
        
        assertTrue(cube.calcVolume() == 10*12*13);
        assertTrue(cube.calcArea() == 10*12*2 + 12*13*2 + 13*10 * 2);
        
        cube = new Cube(-10,12,-13);
        
        assertTrue(cube.calcVolume() == 10*12*13);
        assertTrue(cube.calcArea() == 10*12*2 + 12*13*2 + 13*10 * 2);
    }
    
    @Test
    public void PlaneTest()
    {
        Plane plane = new Plane(10, 13);
        assertTrue(plane.calcVolume() == 0);
        assertTrue(plane.calcArea() == 10*13);
        
        plane = new Plane(10, -13);
        assertTrue(plane.calcVolume() == 0);
        assertTrue(plane.calcArea() == 10*13);
    }
    
    boolean testFloatsAreSame(float a, float b)
    {
        float sub = a - b;
        if (sub < 0.00001)
            return true;
        else if (sub > -0.00001)
            return true;
        else
            return false;
    }
    
    @Test
    public void SphereTest()
    {
        Sphere sphere = new Sphere(10);
        
        assertTrue(testFloatsAreSame(sphere.calcVolume(), (float) Math.PI * 10 * 10 * 10 * 4 / 3));
        assertTrue(testFloatsAreSame(sphere.calcArea(), (float) Math.PI * 10 * 10 * 4));
    }

    @Test(expected=IllegalArgumentException.class)
    public void MainTest()
    {
        Main.calcArea(null);
    }
}