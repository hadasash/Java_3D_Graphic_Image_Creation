package geometries;

import java.util.List;
import static primitives.Util.*;
import primitives.*;

/**
 * 
 * @author Adi and Hadasa
 *
 */

public class Plane extends Geometry
{
	Point3D q0;
	Vector normal;

	/*************** ctors *****************/
	/**
	 * ctor 
	 * @param q0
	 * @param normal
	 */
	public Plane(Point3D q0, Vector normal) {
		super();
		this.q0 = q0;
		this.normal = normal;
	}
	/**
	 * ctor 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Plane(Point3D x,Point3D y,Point3D z)
	{
		super();
		try
		{
			this.q0 = x;				//the point that represents the plane
			Vector v1 = (x.subtract(y));//get one vector on plane
			Vector v2 = (x.subtract(z));//get second vector on plane
			this.normal = v1.crossProduct(v2).normalized();//if v1 and v2 are on the same direction of vector- the cross product and the normal will be zero vector.
		}

		catch (IllegalArgumentException exc) //catch if the normal is the zero vector- its not a plane
		{
			throw new IllegalArgumentException("This is not a Plane/Triangle");
		}
	}

	/*************** get *****************/
	/**
	 * @return q0 of the plane
	 */
	public Point3D getQ0() {
		return q0;
	}

	/**
	 * @param p
	 * @return the normal vector
	 */
	public Vector getNormal(Point3D p) {
		return normal;
	}

	/*************** normalize *****************/
	/**
	 * @return the normal vector
	 */
	public Vector getNormal() {
		return normal;
	}

	/*************** intersections *****************/
	/**
	 * @param ray
	 * @return a list of GeoPoints- intersections of the ray with the plane, and this plane
	 */
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray)
	{
		try {
			Vector vec=q0.subtract(ray.getP0());//creating a new vector according to the point q0 and the starting point of the ray (P0)

			double t=normal.dotProduct(vec);//dot product between the vector we created and the normal of the plane

			if(isZero(normal.dotProduct(ray.getDir()))) //if the dot product equals 0 it means that the ray is parallel (makbil) to the plane
				return null;
			t=t/(normal.dotProduct(ray.getDir()));

			if(t==0) //if the distance between the p0-the start point of the ray and the plane is 0, its not counted in the intersections list
				return null;//no intersections
			else if(t > 0) // the ray crosses the plane
			{
				Point3D p=ray.getPoint(t);//get the new point on the ray, multiplied by the scalar t. p is the intersection point.
				return List.of(new GeoPoint(this,p));//if so, return the point- the intersection
			}
			else // the ray doesn't cross the plane
				return null;	
		}

		catch(Exception ex) //catch exceptions in the vector creation
		{
			return null;
		}
	}

	/*************** admin *****************/
	@Override
	public String toString() {
		return "Plane [q0=" + q0 + ", normal=" + normal + "]";
	}

}
