package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.Geometries;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;

/**
 * 
 * @author Adi and Hadasa
 *
 */

public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() 
	{
		//restart geometries
		Geometries geo=new Geometries(new Plane(new Point3D(0,1,1),new Point3D(1,0,1),new Point3D(0,0,1)),
				new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,0)),
				new Plane(new Point3D(0,1,2),new Point3D(1,0,2),new Point3D(0,0,2)));
		//plane1= parallel to [xy]: z=1, 
		//plane2= [xy]: z=0,  
		//plane3= parallel to [xy]: z=2
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: the middle point is the closest
		Ray ray=new Ray(new Point3D(0, 0, -0.5),new Vector(0,0,1));
		List<Point3D> result=geo.findIntersections(ray);
		Point3D closeP =ray.findClosestPoint(result);
		assertEquals("Wrong close intersection",result.get(1), closeP);
		
		// =============== Boundary Values Tests ================== 
		// TC02: empty list
		ray=new Ray(new Point3D(0, 0, -5),new Vector(0,0,-1));
		result=geo.findIntersections(ray);
		closeP =ray.findClosestPoint(result);
		assertEquals("Wrong close intersection", null, closeP);
		
		// TC03: the first point is the closest
		ray=new Ray(new Point3D(0, 0, 1.5),new Vector(0,0,-1));
		result=geo.findIntersections(ray);
		closeP =ray.findClosestPoint(result);
		assertEquals("Wrong close intersection", result.get(0), closeP);
		
		// TC04: the last point is the closest
		ray=new Ray(new Point3D(0, 0, 2.5),new Vector(0,0,-1));
		result=geo.findIntersections(ray);
		closeP =ray.findClosestPoint(result);
		assertEquals("Wrong close intersection", result.get(2), closeP);
	}

	/**
	 * Test method for {@link primitives.Ray#getClosestGeoPoint(java.util.List)}.
	 */
	@Test
	public void testGetClosestGeoPoint() 
	{
		//restart geometries
		Geometries geo=new Geometries(new Plane(new Point3D(0,1,1),new Point3D(1,0,1),new Point3D(0,0,1)),
				new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,0)),
				new Plane(new Point3D(0,1,2),new Point3D(1,0,2),new Point3D(0,0,2)));
		//plane1= parallel to [xy]: z=1, 
		//plane2= [xy]: z=0,  
		//plane3= parallel to [xy]: z=2
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: the middle point is the closest
		Ray ray=new Ray(new Point3D(0, 0, -0.5),new Vector(0,0,1));
		List<GeoPoint> result=geo.findGeoIntersections(ray);
		GeoPoint closePointAndGeo =ray.getClosestGeoPoint(result);
		assertEquals("Wrong close intersection",result.get(1), closePointAndGeo);
		
		// =============== Boundary Values Tests ================== 
		// TC02: empty list
		ray=new Ray(new Point3D(0, 0, -5),new Vector(0,0,-1));
		result=geo.findGeoIntersections(ray);
		closePointAndGeo =ray.getClosestGeoPoint(result);
		assertEquals("Wrong close intersection", null, closePointAndGeo);
		
		// TC03: the first point is the closest
		ray=new Ray(new Point3D(0, 0, 1.5),new Vector(0,0,-1));
		result=geo.findGeoIntersections(ray);
		closePointAndGeo =ray.getClosestGeoPoint(result);
		assertEquals("Wrong close intersection", result.get(0), closePointAndGeo);
		
		// TC04: the last point is the closest
		ray=new Ray(new Point3D(0, 0, 2.5),new Vector(0,0,-1));
		result=geo.findGeoIntersections(ray);
		closePointAndGeo =ray.getClosestGeoPoint(result);
		assertEquals("Wrong close intersection", result.get(2), closePointAndGeo);
	}
	
}
