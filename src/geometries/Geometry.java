// Adi and Hadasa
package geometries;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface of geometry that has to implement the normal
 * @author hadasa and adi
 */
interface Geometry extends Intersectable
{
	/**
	 * @param point
	 * @return the normal vector
	 */
	public Vector getNormal(Point3D point);
}
