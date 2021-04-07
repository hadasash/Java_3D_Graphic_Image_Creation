// Adi and Hadasa

package geometries;
import java.util.List;

import primitives.*;

/**
 * interface of geometry that implement findIntersections of a ray with the geometry
 * @author Adi & Hadasa
 */
public interface Intersectable 
{
	/**
	 * 
	 * @param ray
	 * @return list of intersections
	 */
	List<Point3D> findIntersections(Ray ray);
	
}
