// Adi and Hadasa
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
	//get ray point and vector
    Point3D rayP = axisRay.getP0();
    Vector rayV = axisRay.getDir();

    //get point on the same level as the given point
    double t = rayV.dotProduct(p.subtract(rayP));

    //if the point is not on the same level then get the point
    //and return the normal
    if(!Util.isZero(t)){
        Point3D o = rayP.add(rayV.scale(t));
        return p.subtract(o).normalized();
    }

    //if the point is on the same level then return normal
    return p.subtract(axisRay.getP0()).normalized();


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