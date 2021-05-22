// Adi and Hadasa
package geometries;
import primitives.*;

/**
 * 
 * @author Adi and Hadasa
 *
 */

class Cylinder extends Tube 
{
	double height;

	/*************** ctor *****************/
	/**
	 * ctor that gets 3 parameters
	 * @param radius
	 * @param ray
	 * @param height
	 */
	public Cylinder(double radius, Ray ray, double height) {
		super(ray, radius);
		this.height = height;
	}

	/*************** calculating functions *****************/

	/**
	 * @return the height of the cylinder
	 */
	public double getHeight() {
		return height;
	}
	
	/*************** get *****************/
	/**
	 * @param p
	 * @return the noraml
	 */
	public Vector getNormal(Point3D p) {
		return null;
	}

	/*************** admin *****************/
	@Override
	public String toString() {
		return "Cylinder [height=" + height + "]";
	}

}
