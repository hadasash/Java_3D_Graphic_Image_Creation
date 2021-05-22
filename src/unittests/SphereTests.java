
// Adi and Hadasa
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import static primitives.Util.*;
import org.junit.Test;
import primitives.*;
import geometries.*;

/**
 * 
 * @author Adi and Hadasa
 *
 */

public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Point3D p= new Point3D(1, 1, 6);
		Point3D p1=new Point3D(1,1,1);
		Sphere s = new Sphere(p1,5);
		Vector v= p1.subtract(p).normalize();

		assertEquals("Bad normal to sphere",v,s.getNormal(p));//regular case
		// =============== Boundary Values Tests ==================
		// 
		try {
			new Sphere(p1,0).getNormal(p);//a case where the radius is 0, the ctor of sphere will throw an exception
			fail("GetNormal() should throw an exception, but it failed");
		} catch (Exception e) {}
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(new Point3D(1, 0, 0),1);
 
		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray's line is outside the sphere (0 points)
		assertEquals("Wrong number of points, ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

		// TC02: Ray starts before and crosses the sphere (2 points)
		Point3D p0 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p1 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
				new Vector(3, 1, 0)));
		assertEquals("Wrong number of points, ray crosses the sphere twice", 2, result.size());
		//check if the calculated points are really equal to the expected-p0 and p1
		boolean flag=false;
		try {
			if (isZero((result.get(0).subtract(p0)).length()) && (isZero((result.get(1).subtract(p1)).length())))
				flag=true;
			else if(isZero((result.get(0).subtract(p1)).length()) && (isZero((result.get(1).subtract(p0)).length())))
				flag=true;
			assertEquals("Wrong points", true, flag);//check if really equal
		}
		catch(IllegalArgumentException e) {}

		// TC03: Ray starts inside the sphere (1 point)
		result = sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0),
				new Vector(3, 1, 0)));
		assertEquals("Wrong number of points, the ray starts inside the sphere", 1, result.size());
		assertEquals("Wrong point", List.of(p1), result);//check if really equal
		
		// TC04: Ray starts after the sphere (0 points)
		assertEquals("Wrong number of points, the ray starts after the sphere", null, sphere.findIntersections(new Ray(new Point3D(5, 2, 0), new Vector(3, 1, 0))));

		// =============== Boundary Values Tests ==================  

		// **** Group: Ray's line crosses the sphere (but not the center)
		// TC05: Ray starts at sphere and goes inside (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0),new Vector(-1, 0, 1)));
		assertEquals("Wrong point, the ray starts at sphere and goes inside",List.of(new Point3D(1, 0, 1)),result);
		// TC06: Ray starts at sphere and goes outside (0 points)
		assertEquals("Wrong point, the ray starts at sphere and goes outside", null,sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))));

		// **** Group: Ray's line goes through the center
		// TC07: Ray starts before the sphere (2 points)
		result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX()> result.get(1).getX())//change the order of points to match our knowledge
        	result = List.of(result.get(1), result.get(0));
		assertEquals("wrong points", List.of(new Point3D(0,0,0), new Point3D(2,0,0)), result);//check if really equal
		// TC08: Ray starts at sphere and goes inside (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0),new Vector(0, -1, 0)));
		assertEquals("Wrong point, the ray starts at sphere and goes inside",List.of(new Point3D(1, -1, 0)),result);
		// TC09: Ray starts inside (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0),new Vector(0, -1, 0)));
		assertEquals("wrong point, the ray starts inside",List.of(new Point3D(1, -1, 0)),result);
		// TC10: Ray starts at the center (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0),new Vector(0, -1, 0)));
		assertEquals("wrong point, the ray starts at the center",List.of(new Point3D(1, -1, 0)),result);
		// TC11: Ray starts at sphere and goes outside (0 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0),new Vector(0, 1, 0)));
		assertEquals("wrong point, the ray starts at sphere and goes outside",null,result);
		// TC12: Ray starts after sphere (0 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 2, 0),new Vector(0, 1, 0)));
		assertEquals("wrong point, the ray starts after sphere",null,result);
		
		// **** Group: Ray's line is tangent (=mashik) to the sphere- no intersections counted with the sphere.
		// TC13: Ray starts before the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(0, 1, 0),new Vector(0, 1, 0)));
		assertEquals("wrong point, tangent line, ray starts before sphere",null,result);
		// TC14: Ray starts at the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0),new Vector(0, 1, 0)));
		assertEquals("wrong point, tangent line, ray starts at sphere",null,result);
		// TC15: Ray starts after the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(2, 1, 0),new Vector(0, 1, 0)));
		assertEquals("wrong point, tangent line, ray starts after sphere",null,result);
		
		// **** Group: Special cases
		// TC16: Ray's line is outside, ray is orthogonal to a ray start from the sphere's center. not tangent.
		result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),new Vector(0, 0, -1)));
		assertEquals("wrong point, the ray is orthogonal to the continuing line starts from the center",null,result);
	}


}
