// Adi and Hadasa
package primitives;

/**
 * 
 * @author Adi and Hadasa
 *
 */

public class Vector 
{
	Point3D head;

	/*************** ctors *****************/
	/**
	 * ctor that gets 1 parameter
	 * @param head
	 */
	public Vector(Point3D head) {
		super();

		if (Point3D.ZERO.equals(head))
			throw new IllegalArgumentException("Illigel vector - vector cannot be (0,0,0)");
		else
			this.head = head;
	}

	/**
	 * ctor that gets 3 double parameteres
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(double x, double y, double z) /* ctor with 3 doubles */
	{
		super();

		Point3D p = new Point3D(x, y, z);
		if (Point3D.ZERO.equals(p))
			throw new IllegalArgumentException("Illigel vector - vector cannot be (0,0,0)");
		else
			head = p;
	}

	/**
	 * ctor that gets 3 Coordinate parameteres
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) /* ctor with 3 coordinate */
	{
		super();

		Point3D p = new Point3D(x, y, z);
		if (Point3D.ZERO.equals(p))
			throw new IllegalArgumentException("Illigel vector - vector cannot be (0,0,0)");
		else
			head = p;
	}

	/*************** get *****************/
	/**
	 * @return the head of the vector
	 */
	public Point3D getHead() {
		return head;
	}

	/*************** calculating functions *****************/
	/**
	 * add the wanted vector to it's head
	 * @param v
	 * @return the new vector
	 */
	public Vector add(Vector v) {
		return (new Vector(head.add((v))));
	}

	/**
	 * subtract the wanted vector from it's head
     * @param v
	 * @return the vector
	 */
	public Vector subtract(Vector v) {
		return (head.subtract(v.getHead()));
	}

	/**
	 * @param s
	 * @return the wanted vector by multipile each of it's coordinates by s
	 */
	public Vector scale(double s)/* multipale scale with vector using ctor with 3 doubles */
	{
		return (new Vector(head.x.coord * s, head.y.coord * s, head.z.coord * s));
	} 

	/**
	 * @param v
	 * @return a new vector of the cross product
	 */
	public Vector crossProduct(Vector v) {
		Coordinate cx = new Coordinate(
				head.y.coord * v.getHead().z.coord - head.z.coord * v.getHead().y.coord);/* (y1*z2-y2*z1) */
		Coordinate cy = new Coordinate(
				head.z.coord * v.getHead().x.coord - head.x.coord * v.getHead().z.coord);/* (-(x1*z2-z1*x2)) = z1*x2-x1*z2 */
		Coordinate cz = new Coordinate(
				head.x.coord * v.getHead().y.coord - head.y.coord * v.getHead().x.coord);/* (x1*y2-y1*x2) */

		return (new Vector(cx, cy, cz));
	}

	/**
	 * @param v
	 * @return the scallar
	 */
	public double dotProduct(Vector v) {
		double cx = head.x.coord * v.getHead().x.coord;
		double cy = head.y.coord * v.getHead().y.coord;
		double cz = head.z.coord * v.getHead().z.coord;

		double sum = cx + cy + cz;

		return (sum);
	}

	/**
	 * @return the squared of the length
	 */
	public double lengthSquared()// distance from reshit haztirim
	{
		return (head.distanceSquared(Point3D.ZERO));//return the distance of the head from zero
	}

	/**
	 * @return the length
	 */
	public double length() 
	{
		return (Math.sqrt(lengthSquared()));
	}

	/*************** normalize *****************/
	/**
	 * @return the current normalize vector
	 */
	public Vector normalize() {
		double len = length();
		this.head = new Point3D(head.x.coord / len, head.y.coord / len, head.z.coord / len);
		return this;
	}

	/**
	 * @return a new normalize vector
	 */
	public Vector normalized() {
		return new Vector(normalize().head);
	}

	/*************** admin *****************/
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
		Vector other = (Vector) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Vector=" + head;
	}
}
