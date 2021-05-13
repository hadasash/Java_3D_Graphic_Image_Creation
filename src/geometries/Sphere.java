package geometries;
import java.util.List;
import static primitives.Util.*;
import primitives.*;

/**
 * 
 * @author Adi and Hadasa
 *
 */

public class Sphere extends Geometry
{
	Point3D center;
	double radius;

	/*************** ctor *****************/
	/**
	 * ctor 
	 * @param center
	 * @param radius
	 */
	public Sphere(Point3D center, double radius) {
		super();
		if(Util.isZero(radius) || radius < 0)
			throw new IllegalArgumentException("Zero or negative radius");
		this.center = center;
		this.radius = radius;
	}

	/*************** normalize *****************/
	/**
	 * @return the normal
	 */
	public Vector getNormal(Point3D p) 
	{
		return center.subtract(p).normalized();//the normal to sphere is the subtraction of the given point from the center.
	}

	/*************** gets *****************/
	/**
	 * @return the center
	 */
	public Point3D getCenter() {
		return center;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/*************** admin *****************/
	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}

	/*************** intersections *****************/
	/**
	 * @param ray
	 * @return a list of GeoPoints- intersections of the ray with the sphere, and this sphere
	 */
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) 
	{
		
		Point3D p0 = ray.getP0();		//ray point
		Vector v = ray.getDir();		//ray vector

		if(p0.equals(center))       	//if the starting point of the ray is the center
			return List.of(new GeoPoint(this,ray.getPoint(radius)));
		
		Vector u=center.subtract(p0);	//the vector between center and ray
		double tm=v.dotProduct(u); 		//the scale for the ray in order to get parallel to the sphere center
		double d=Math.sqrt(u.lengthSquared()-tm*tm);//get the distance between the ray and the sphere center
		//check if d is bigger then radius the ray doesn't cross the sphere
		
		if (d>radius)
			return null;
		double th=Math.sqrt(radius*radius-d*d);// Pythagoras
		double t1=tm+th;
		double t2=tm-th;
		if(t1>0&&t2>0&&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v))&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v))) //if orthogonal -> no intersection
			return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
		else if(t1>0&&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v))) //if t1 not orthogonal and positive
			return List.of(new GeoPoint(this,ray.getPoint(t1)));
		else if(t2>0&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v))) //if t2  not orthogonal and positive
			return List.of(new GeoPoint(this,ray.getPoint(t2)));
		else
			return null;
	}



}
