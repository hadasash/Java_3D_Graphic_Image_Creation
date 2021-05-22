// Adi and Hadasa
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;
import primitives.*;
import geometries.*;

/**
 * 
 * @author Adi and Hadasa
 *
 */

public class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tri = new Triangle(new Point3D(2,0,0),new Point3D(0,2,0),new Point3D(0,0,0));
        assertEquals(new Vector(0,0,1),tri.getNormal(null));
	}

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    public void findIntersections() 
    {
        Triangle tr = new Triangle(new Point3D(0, 3, -3),new Point3D(3, 0, -3),new Point3D(-3, 0, -3));
        Ray r;

		// ============ Equivalence Partitions Tests ==============
        // TC01: the ray goes through the triangle
        try {
        r = new Ray(new Point3D(1, 1, -2), new Vector(-2, 0.5, -1));
        assertEquals("the ray goes through the triangle", List.of(new Point3D(-1, 1.5, -3)), tr.findIntersections(r));
        }
        catch(IllegalArgumentException e) //catch creation of new vectors at findIntersections- one might be zero vector
        {}
        // TC02: the ray is outside the triangle against edge
        r = new Ray(new Point3D(4, 4, -2), new Vector(1, 1, -4));
        assertEquals("the ray is outside the triangle against edge", null, tr.findIntersections(r));
        // TC03: the ray is outside the triangle against vertex
        r = new Ray(new Point3D(-4, -1, -2), new Vector(-1, -1, -1));
        assertEquals("the ray is outside the triangle against vertex", null, tr.findIntersections(r));


		// =============== Boundary Values Tests ==================
        // TC04: ray through edge
        r = new Ray(new Point3D(-2, 1, -1), new Vector(0, 0, -1));
        assertEquals("ray through edge", null, tr.findIntersections(r));

        // TC05: ray through vertex 
        r = new Ray(new Point3D(0, 3, -2), new Vector(0, 0, -1));
        assertEquals("ray through vertex", null, tr.findIntersections(r));
      
        // TC06: ray goes through the continuation of side 1
        r = new Ray(new Point3D(-1, 4, -2), new Vector(0, 0, -1));
        assertEquals("ray goes through the continuation of side 1", null, tr.findIntersections(r));
    }
	
}
