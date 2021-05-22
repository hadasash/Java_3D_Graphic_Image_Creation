package primitives;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;
import java.util.List;

/**
 * 
 * @author Adi and Hadasa
 *
 */

public class Ray {
	Point3D p0;
	Vector dir;

	//for constructing reflected and refracted rays
	private static final double DELTA = 0.01;
	
	/*************** ctor *****************/
	/**
	 * ctor that gets 2 parameteres
	 * @param p0
	 * @param dir
	 */
	public Ray(Point3D p0, Vector dir) 
	{
		super();
		this.p0 = p0;
		this.dir = dir.normalized();

	}
	
	/**
     * ctor that gets 3 parameteres
     *@param vector
     * @param point
     * @param normal
     * @return point + normal.scale(±DELTA)
     */
	public Ray(Point3D point, Vector direction, Vector normal) 
	{
		this.dir = direction.normalized();
		double nV = normal.dotProduct(direction);
		Vector delta = normal.scale(nV >= 0 ? DELTA : -DELTA);
		this.p0 = point.add(delta);

    }

	/*************** get *****************/
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
	 * @param t- scalar
	 * @return the new point->p0+t*dir. useful for findIntersection methods.
	 * this func is like "scale" of vector, but adds the p0= beginning point of the ray.
	 */
	public Point3D getPoint(double t)
	{
		Point3D tmp=new Point3D(p0.x,p0.y,p0.z);
		return isZero(t) ? p0 : tmp.add(dir.scale(t));//takes the beginning of the ray and adds the vector*scalar point that we get.
	}

	/**
	 * this function gets a list of (intersection) points,
	 * and returns the closest point from them to p0-beginning of this ray. 
	 * @param points
	 * @return closestP
	 */
	public Point3D findClosestPoint(List<Point3D> points)
	{
		if (points==null)//if the list is empty
			return null;

		Point3D closestP=points.get(0);			//take the 1st point in the beginning
		double min=p0.distance(points.get(0));	

		for(int i=0; i<points.size(); i++) 		//move on all the points
		{
			if (p0.distance(points.get(i))<min) //change the closest point if the dis < min
			{
				min=p0.distance(points.get(i));
				closestP=points.get(i);		    
			}
		}
		return closestP;					    //return is the closest point-with min distance from p0.
	}

	/**
	 * same as the function "findClosestPoint", but works with GeoPoints.
	 * @param points
	 * @return the GeoPoint in which its point is the closest to p0 of the ray.
	 */
	public GeoPoint getClosestGeoPoint(List<GeoPoint> points)
	{
		if (points==null)//if the list is empty
			return null;

		GeoPoint closestP=points.get(0);			//take the 1st point in the beginning. point and geometry.
		double min=p0.distance(points.get(0).point);	

		for(int i=0; i<points.size(); i++) 		//move on all the points
		{
			if (p0.distance(points.get(i).point)<min) //change the closest point if the dis < min
			{
				min=p0.distance(points.get(i).point);
				closestP=points.get(i);		    
			}
		}
		return closestP;	//return the closest point(and the geometry it intersects)-with min distance from p0.
	
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
