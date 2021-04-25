// Adi and Hadasa
package primitives;
import static primitives.Util.*;

import java.util.List;

/**
 * 
 * @author Adi and Hadasa

 *
 */

public class Ray {
	Point3D p0;
	Vector dir;

	/*************** ctor *****************/
	/**
	 * ctor that gets 2 parameteres
	 * @param p0
	 * @param dir
	 */
	public Ray(Point3D p0, Vector dir) {
		super();
		this.p0 = p0;
		this.dir = dir.normalized();

	}
	/**
	 * @return p0 of the ray
	 */
	public Point3D getP0() {
		return p0;
	}
	/**
	 * @return dir vector
	 */
	public Vector getDir() {
		return dir;
	}
	
	/**
	 * @param t scalar
	 * @return  new point
	 */
	public Point3D getPoint(double t)
	{//Like Vector's Scalar but with a starting point in mind
		Point3D tmp=new Point3D(p0.x,p0.y,p0.z);
		return isZero(t) ? p0 : tmp.add(dir.scale(t));
	}
	public Point3D findClosestPoint(List<Point3D> points)
	{
		if (points==null)
			return null;

		Point3D closestP=points.get(0);			//put the first point on the list as the closest point
		double min=p0.distance(points.get(0));	

		for(int i=0; i<points.size(); i++) 		
		{
			if (p0.distance(points.get(i))<min) //If the distance is less than the distance of the nearest point replaces them
			{
				min=p0.distance(points.get(i));
				closestP=points.get(i);		    
			}
		}
		return closestP;					    //return is the closest point-with min distance from p0.
	}


	/*************** Admin *****************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Ray other = (Ray) obj;
		if (dir == null) {
			if (other.dir != null)
				return false;
		} else if (!dir.equals(other.dir))
			return false;
		if (p0 == null) {
			if (other.p0 != null) {
				return false;
			}
		} else if (!p0.equals(other.p0)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Ray [Point=" + p0 + ", Vector=" + dir + "]";
	}

}
