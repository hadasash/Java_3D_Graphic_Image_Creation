// Adi and Hadasa
package geometries;
import java.util.List;
import static primitives.Util.*;
import primitives.*;

/**
 * 
 * @author hadasa and adi
 *
 */

public class Sphere implements Geometry
{
	Point3D center;
	double radius;

	/*************** ctor *****************/
	/**
	 * ctor that gets 2 parameters
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
	public Vector getNormal(Point3D p) {
		return center.subtract(p).normalized();//n=center-p
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
	 * @return a list of intersections of the ray with the sphere
	 */
	@Override
	public List<Point3D> findIntersections(Ray ray) 
	{
		Point3D p0 = ray.getP0();
		Vector v = ray.getDir();

		if(p0.equals(center))//if is the center
			return List.of(ray.getPoint(radius));//return intersection 
		Vector u=center.subtract(p0);// the vector between center and ray
		double tm=v.dotProduct(u); //the scale for the ray in order to get parallel to the sphere center
		double d=Math.sqrt(u.lengthSquared()-tm*tm);// distance between the ray and the sphere center
		// if d is bigger then radius-the ray doesn't cross the sphere
		if (d>radius)
			return null;
		double th=Math.sqrt(radius*radius-d*d);//pitagoras
		double t1=tm+th;
		double t2=tm-th;
		if(t1>0&&t2>0&&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v))&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v))) //if orthogonal -> no intersection
			return List.of(ray.getPoint(t1),ray.getPoint(t2));
		else if(t1>0&&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v))) //if only t1 is not orthogonal and positive
			return List.of(ray.getPoint(t1));
		else if(t2>0&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v))) //if only t2 is not orthogonal and positive
			return List.of(ray.getPoint(t2));
		else
			return null;
	}



}
