package geometries;
import primitives.*;

class Cylinder extends Tube
{
double height;
/**
 *The function receives a point and returns the normal vector to the cylinder.
 * 
 * @param p The point. 
 */
public Vector getNormal(Point3D p) 
{
	return null;
}
/**
 *constractor with 3 params.
 * @param radius radius of the cylinder.
 * @param ray ray of the cylinder.
 * @param height height of the cylinder.
 */
public Cylinder(double radius, Ray ray, double height)
{
	super(ray,radius);
	this.height = height;
}
/**
 * 
 * @return get height.
 */
public double getHeight() 
{
	return height;
}
/**
 * ovveride the toString.
 */
@Override
public String toString() 
{
	return "Cylinder [height=" + height + "]";
}


}
