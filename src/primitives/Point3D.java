// Adi and Hadasa
package primitives;

/**
 * 
 * @author  Adi and Hadasa

 *
 */

public class Point3D {
	Coordinate x;
	Coordinate y;
	Coordinate z;

	public final static Point3D ZERO = new Point3D(0, 0, 0);

	/*************** ctors *****************/
	/**
	 * ctor that gets 3 parameters
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * ctor that gets 3 parameters
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(double a, double b, double c) {
		x = new Coordinate(a);
		y = new Coordinate(b);
		z = new Coordinate(c);
	}

	public double getX() {
		return x.coord;
	}
	public double getY() {
		return y.coord;
	}
	public double getZ() {
		return z.coord;
	}

	/*************** calculating functions *****************/
	/**
	 * @param v
	 * @return the new point3d - by plus 
	 */
	public Point3D add(Vector v) {
		Coordinate cx;
		Coordinate cy;
		Coordinate cz;

		cx = new Coordinate(x.coord + v.getHead().x.coord);
		cy = new Coordinate(y.coord + v.getHead().y.coord);
		cz = new Coordinate(z.coord + v.getHead().z.coord);

		return new Point3D(cx, cy, cz);
	}

	/**
	 * @param p
	 * @return the new vector by minus: this point-the given point
	 */
	public Vector subtract(Point3D p) {
		Coordinate cx;
		Coordinate cy;
		Coordinate cz;

		cx = new Coordinate(x.coord - p.x.coord);
		cy = new Coordinate(y.coord - p.y.coord);
		cz = new Coordinate(z.coord - p.z.coord);

		return new Vector(new Point3D(cx, cy, cz));
	}

	/**
	 * calculate the distance by sending to dotProduct the wanted vector & reutrn it
	 * @param p
	 * @return the distance^2
	 */
	public double distanceSquared(Point3D p) {
		/*Vector v = p.subtract(ZERO);
		return v.dotProduct(v);*/

		Vector v = this.subtract(p); 
		return v.dotProduct(v);
		
		/*return this.x.subtract(p).multiply(this.x.subtract(p.x))
				.add(y.subtract(p.y).multiply(y.subtract(p.y))
				.add(z.subtract(p.z).multiply(z.subtract(p.z)).get();*/
	
	}

	/**
	 * calculate the distance by math.sqtr & reutrn the sqrt of the wanted point
	 * @param p
	 * @return the distance
	 */
	public double distance(Point3D p) {
		double sqrt = Math.sqrt(distanceSquared(p));
		return sqrt;
	}

	/*************** admin *****************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		if (z == null) {
			if (other.z != null)
				return false;
		} else if (!z.equals(other.z))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}
}
