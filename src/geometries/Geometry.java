package geometries;

import primitives.Point3D;
import primitives.Vector;

interface Geometry 
{
	/**
	 * A function that all the bodies in the geometric department will override and realize according to the three-dimensional body.
	 * @param point The point for which the function calculates the normal with the body.
	 * @return The normal
	 */
	public Vector getNormal(Point3D point) ;
}
