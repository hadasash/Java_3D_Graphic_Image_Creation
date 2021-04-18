package unittests;

import static org.junit.Assert.*;
import org.junit.Test;
import elements.*;
import primitives.*;
import geometries.*;
import geometries.Intersectable;
import java.util.List;

/**
 * 
 * @author Adi Ashkenazi & Hadasa Fox
 *
 */
public class IntegrationTests
{
	Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));//position of the camera 1
	Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));//position of the camera 2
	//All the tests we will perform will be on a 3 * 3 viewing plane
	int Nx =3;
	int Ny =3;
	
	/**
	 * help function : receives an intersectable geometry, and the camera, calculates the number of intersections.
	 * @param geometry -  Geometry for which intersections can be calculated
	 * @param cam - the camera
	 * @return
	 */
	private int constructRayAndCountNumIntersections(Intersectable geometry, Camera cam)
	{
		List<Point3D> results;//all the intersection
		int count = 0;
		for (int i = 0; i < Nx; ++i) //width
		{
			for (int j = 0; j < Ny; ++j) //height
			{
				Ray ray = cam.setVpSize(3,3).setVpDistance(1).constructRayThroughPixel(Nx,Ny,j,i);
				results = geometry.findIntersections(ray);//calculate all intersections of a ray and the geometry
				if (results != null)		//If there are points of intersection with the geometry adds the number of points to the count		 
					count += results.size();			 
			}
		}
		return count;//number intersections of all rays with the geometry
	}

	//==========tests for triangle=========/
	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
	 */
	@Test
	public void constructRayThroughPixelWithTriangle() 
	{
		//TC 01: small triangle centered in front of camera- one intersection through the middle ray.
		Triangle tr1 =  new Triangle( new Point3D(-1, 1, 2),new Point3D(1, 1, 2),new Point3D(0, -1, 2));
		int count = constructRayAndCountNumIntersections(tr1,cam2);
		assertEquals("too bad",1,count);
		System.out.println("count: "+count);
		
		//TC 02: higher, narrow triangle, placed in front of 2 centered- upper pixels. 2 intersections.
		Triangle tr2 =  new Triangle( new Point3D(-1, 1, 2),new Point3D(1, 1, 2),new Point3D(0, -20, 2));
		count = constructRayAndCountNumIntersections(tr2,cam2);
		assertEquals("too bad",2,count);
		System.out.println("count: "+count);
	}
	
	//==========tests for sphere=========/
	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}
	 */
	@Test 
	public void constructRayThroughPixelWithSphere() 
	{
		//TC 01: radius=1, 2 intersections from the middle ray
		Sphere sph1 =  new Sphere( new Point3D(0, 0, 3),1);
		int count = constructRayAndCountNumIntersections(sph1,cam1);
		assertEquals("too bad",2,count);
		System.out.println("count: "+count);

		//TC 02: radius=2.5, 2 intersections from each ray- total=18
		Sphere sph2 =  new Sphere( new Point3D(0, 0, 2.5),2.5);
		count = constructRayAndCountNumIntersections(sph2,cam2);
		assertEquals("too bad",18,count);
		System.out.println("count: "+count);

		//TC 03: radius=2, 2 intersections from all 5 centered rays #, total=10
		Sphere sph3 =  new Sphere( new Point3D(0, 0, 2),2);
		count = constructRayAndCountNumIntersections(sph3,cam2);
		assertEquals("too bad",10,count);
		System.out.println("count: "+count);

		//TC 04: radius=4, camera is inside the sphere, 1 intersection from each ray (one side), total- 9
		Sphere sph4 =  new Sphere( new Point3D(0, 0, 1),4);
		count = constructRayAndCountNumIntersections(sph4,cam2);
		assertEquals("too bad",9,count);
		System.out.println("count: "+count);

		//TC 05: radius=0.5, sphere behind the camera, no intersections.
		Sphere sph5 =  new Sphere( new Point3D(0, 0, -1),0.5);
		count = constructRayAndCountNumIntersections(sph5,cam2);
		assertEquals("too bad",0,count);
		System.out.println("count: "+count);
	}

	//==========tests for plane=========/
	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}
	 */
	@Test
	public void constructRayThroughPixelWithPlane()
	{
		//TC 01: plane orthogonal to vTo, in front of camera. 9 intersections- all rays.
		Plane pl1 =  new Plane( new Point3D(0, 0, 7),new Vector(0,0,1));
		int count = constructRayAndCountNumIntersections(pl1,cam2);
		assertEquals("too bad",9,count);
		System.out.println("count: "+count);

		//TC 02: plane is tilted but still all rays intersect it. total: 9.
		Plane pl2 =  new Plane( new Point3D(0, 0, 2),new Vector(0,1,3));
		count = constructRayAndCountNumIntersections(pl2,cam2);
		assertEquals("too bad",9,count);
		System.out.println("count: "+count);

		//TC 03: plane is so tilted so the rays through the bottom row of pixels- don't reach the plane. total: 6.
		Plane pl3 =  new Plane( new Point3D(0, 0, 2),new Vector(0,3,1));
		count = constructRayAndCountNumIntersections(pl3,cam2);
		assertEquals("too bad",6,count);
		System.out.println("count: "+count);
	}

}


