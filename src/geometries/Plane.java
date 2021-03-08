package geometries;
import primitives.*;

public class Plane 
{
Point3D q0;
Vector normal;
/**
 * A constractor who gets the data on the plane.
 * @param q0 The point of the plane.
 * @param normal The normal to the plane.
 */
public Plane(Point3D q0, Vector normal) 
{
	super();
	this.q0 = q0;
	this.normal = normal;
}
/**
 * constractor with 3 Point3D.
 * @param p1 point one. 
 * @param p2 point two.
 * @param p3 point three.
 */
public Plane(Point3D p1, Point3D p2, Point3D p3) 
{
	super();
	
	try 
	{
		this.q0=p1;
		Vector v1=(p1.subtract(p2));
		Vector v2=(p1.subtract(p3));		
		this.normal=v1.crossProduct(v2).normalized();
	}
		
    catch (IllegalArgumentException exc)
    {
        throw new IllegalArgumentException("This is not a Plane/Triangle");
	}
}
/**
 * get q0.
 * @return q0
 */
public Point3D getQ0() 
{
	return q0;
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
 * A function that all the bodies in the geometric department will override and realize according to the three-dimensional body.
 * @return The normal
 */
public Vector getNormal() 
{
	return normal;
}
/**
 * ovveride the toString.
 */
@Override
public String toString() 
{
	return "Plane [q0=" + q0 + ", normal=" + normal + "]";
}


}
