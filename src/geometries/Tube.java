package geometries;
import primitives.*;

public class Tube 
{
Ray axisRay;
double radius;
/**
 *  * A constractor who gets the data on the Tube.
 * @param axisRay ray of the tube.
 * @param radius radius of the tube.
 */
public Tube(Ray axisRay, double radius) 
{
	super();
	this.axisRay = axisRay;
	this.radius = radius;
}
/**
 * A function that all the bodies in the geometric department will override and realize according to the three-dimensional body.
 * @param p The point for which the function calculates the normal with the body.
 * @return The normal
 */
public Vector getNormal(Point3D p) 
{
	return null;
}
/**
 * get axis ray.
 * @return ray.
 */
public Ray getAxisRay() 
{
	return axisRay;
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
	return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
}

}