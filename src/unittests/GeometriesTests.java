// Adi and Hadasa
package unittests;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import geometries.Geometries;
import geometries.*;
import primitives.*;

/**
 * 
 * @author Adi and Hadasa
 *
 */

public class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() 
	{
		Geometries geo=new Geometries();
		//we implemented findIntersections of sphere, triangle and plane.
		Geometries geo2=new Geometries(new Sphere(new Point3D(4,0,0),5),new Triangle(new Point3D(1,4,0),new Point3D(1,2,0),new Point3D(5,2,0)),new Plane(new Point3D(1,2,0),new Point3D(0,7,0),new Point3D(1,0,0)));

		// ============ Equivalence Partitions Tests ==============
		// TC01: Some (but not all) geometries are cut  
		List<Point3D> re = geo2.findIntersections(new Ray(new Point3D(2, 5, 4),
				new Vector(2,-4,-4)));
		assertEquals("Wrong number of points", 3, re.size());
		// =============== Boundary Values Tests ==================
		//TC11:Empty geometries collection
		assertEquals("Wrong number of points", null,geo.findIntersections(new Ray(new Point3D(2, 5, 4),
				new Vector(2,-4,-4))));
		//TC12:No shape is cut
		assertEquals("Wrong number of points", null,geo2.findIntersections(new Ray(new Point3D(0, 0, 7),
				new Vector(1,0,0))));
		//TC13:One shape is cut
		re = geo2.findIntersections(new Ray(new Point3D(0, 0, 10),
				new Vector(-8,1,-10)));
		assertEquals("Wrong number of points",1 , re.size());
		//TC14:All shapes are cut
		re = geo2.findIntersections(new Ray(new Point3D(2, 5, 4),
				new Vector(0,-2,-4)));
		assertEquals("Wrong number of points",4 , re.size());
	}

}
