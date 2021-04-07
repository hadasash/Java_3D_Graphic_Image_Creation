// Adi and Hadasa
package geometries;
import primitives.*;
import java.util.List;
import static primitives.Util.*;

/**
 * 
 * @author  Adi and Hadasa

 *
 */

public class Triangle extends Polygon 
{
	/*************** ctor *****************/

	/**
	 * ctor that gets 3 points
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}

	/*************** gets *****************/
	/**
	 * @return first point of triangle
	 */
	public Point3D getPoint1() {
		return super.vertices.get(0);
	}

	/**
	 * @return second point of triangle
	 */
	public Point3D getPoint2() {
		return super.vertices.get(1);
	}

	/**
	 * @return third point of triangle
	 */
	public Point3D getPoint3() {
		return super.vertices.get(2);
	}

	/*************** admin *****************/
	@Override
	public String toString() {
		return "Tri{" + "P1=" + getPoint1() + ", P2=" + getPoint2() + ", P3=" + getPoint3() + '}';
	}
	
	/*************** intersections *****************/
	/**
	 * @param ray
	 * @return a list of intersections of the ray with the triangle
	 */
	@Override
	public List<Point3D> findIntersections(Ray ray)
	{

		List<Point3D> intersections = plane.findIntersections(ray);//find intersections with the plane
		if (intersections == null) return null;//no intersections

		Point3D p0 = ray.getP0();
		Vector v = ray.getDir();

		//v1 v2 v3 are the vectors between p0 to each one of the triangle vertices to create a pyramid
		Vector v1 = vertices.get(0).subtract(p0).normalized();
		Vector v2 = vertices.get(1).subtract(p0).normalized();
		Vector v3 = vertices.get(2).subtract(p0).normalized();
		
		double s1 = v.dotProduct(v1.crossProduct(v2));
		if (isZero(s1)) return null;//the ray is on the plane v1-v2, it means its not inside the triangle but on its edge (or on the vertex, or on the edge continue)
		double s2 = v.dotProduct(v2.crossProduct(v3));
		if (isZero(s2)) return null;//the ray is on  the plane v2-v3, it means its not inside the triangle but on its edge 
		double s3 = v.dotProduct(v3.crossProduct(v1));
		if (isZero(s3)) return null;//the ray is on the plane v3-v1, it means its not inside the triangle but on its edge 

		if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) //if all the s1,s2,s3 positive or negative return the ray intersects the triangle.
			return intersections;
		else
			return null;//the ray is on the plane but outside the triangle
	}

}


