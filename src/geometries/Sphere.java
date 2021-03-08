package geometries;
import primitives.Point3D;
import primitives.Vector;

class Sphere 
{
Point3D center;
double radius;
/**
 *  A constractor who gets the data on the sphere.
 * @param center The center of the sphere.
 * @param radius The radius of the sphere.
 */
public Sphere(Point3D center, double radius) 
{
	super();
	this.center = center;
	this.radius = radius;
}
/**
 * A function that all the bodies in the geometric department will override and realize according to the three-dimensional body.
 * @param point The point for which the function calculates the normal with the body.
 * @return The normal
 */
public Vector getNormal(Point3D p) 
{
	return null;
}
/**
 * get center.
 * @return center.
 */

public Point3D getCenter() 
{
	return center;
}
/**
 * get radius.
 * @return radius.
 */

public double getRadius() 
{
	return radius;
}
/**
 * ovveride the toString.
 */
@Override
public String toString() 
{
	return "Sphere [center=" + center + ", radius=" + radius + "]";
}
}
