// Adi and Hadasa
package primitives;
import static primitives.Util.*;

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
	 * @param t- scalar
	 * @return the new point->p0+t*dir
	 */
	public Point3D getPoint(double t)
	{
		Point3D tmp=new Point3D(p0.x,p0.y,p0.z);
		return isZero(t) ? p0 : tmp.add(dir.scale(t));//takes the beginning of the ray and adds the vector*scalar point that we get.
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
