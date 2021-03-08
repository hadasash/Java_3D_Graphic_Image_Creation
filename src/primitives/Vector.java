package primitives;

public class Vector 
{
Point3D head;
/**
 * constractor with 3 doubles.
 * @param x x.
 * @param y y.
 * @param z z.
 */
public Vector(double x,double y,double z) 
{
	super();

	Point3D p=new Point3D(x,y,z);
	if(Point3D.ZERO.equals(p))
		throw new IllegalArgumentException("Illigel vector - vector cannot be (0,0,0)") ;
	else
		head=p;
}
/**
 * constractor with 3 coordinates.
 * @param x x.
 * @param y y.
 * @param z z.
 */
public Vector(Coordinate x,Coordinate y,Coordinate z)
{
	super();

	Point3D p=new Point3D(x,y,z);
	if(Point3D.ZERO.equals(p))
		throw new IllegalArgumentException("Illigel vector - vector cannot be (0,0,0)") ;
	else
		head=p;
}
/**
 *  * A constractor who gets the data on the Vector.
 * @param head head.
 */
public Vector(Point3D head) 
{
	super();

	if(Point3D.ZERO.equals(head))
		throw new IllegalArgumentException("Illigel vector - vector cannot be (0,0,0)") ;
	else
		this.head=head;
}
/**
 * get head.
 * @return head of the vector.
 */
public Point3D getHead() 
{
	return head;
}
/**
 * A function that receives a vector and connects two vectors.
 * @param v the one vectors.
 * @return Connection between two vectors.
 */
public Vector add(Vector v)
{
	return (new Vector(head.add((v))));	
}
/**
 * A function that receives a vector and subtracts between two vectors.
 * @param v the one vectors.
 * @return Subtraction between two vectors.
 */
public Vector subtract(Vector v)
{
	return (head.subtract(v.getHead()));	
}
/**
 * A function that receives a scalar and performs a doubling of the vector in the scalar it received.
 * @param s the scalar.
 * @return Vector.
 */
public Vector scale(double s)
{
	return (new Vector(head.x.coord*s,head.y.coord*s,head.z.coord*s));
}
/**
 * A function that performs a vector product.
 * @param v vector.
 * @return Vector after vector product.
 */
public Vector crossProduct(Vector v)
{
	Coordinate cx=new Coordinate(head.y.coord*v.getHead().z.coord-head.z.coord*v.getHead().y.coord);/*(y1*z2-y2*z1)*/
	Coordinate cy=new Coordinate(-(head.x.coord*v.getHead().z.coord-head.z.coord*v.getHead().x.coord));/*(-(X1*z2-Z1*X2))*/
	Coordinate cz=new Coordinate(head.x.coord*v.getHead().y.coord-head.y.coord*v.getHead().x.coord);/*(x1*y2-y1*x2)*/
	
	return (new Vector(cx,cy,cz)) ;
}
/**
 * A function that performs a scalar product.
 * @param v vector.
 * @return Vector after scalar product.
 */
public double dotProduct(Vector v)
{
	double cx=head.x.coord*v.getHead().x.coord;
	double cy=head.y.coord*v.getHead().y.coord;
	double cz=head.z.coord*v.getHead().z.coord;
	
	double sum=cx+cy+cz;
	return (sum);
}
/**
 * A function that receives a vector and returns the root of the length between two vectors.
 * @return lenght Squared.
 */
public double lenghtSquared()
{
	return (head.distanceSquared(Point3D.ZERO));
}
/**
 * A function that receives a vector and returns the length between two vectors.
 * @return length.
 */
public double lenght()
{
	return (Math.sqrt(lenghtSquared()));
}
/**
 * A function that normalizes the resulting vector.
 * @return The normalized vector.
 */
public Vector normalize() 
{
	double len=lenght();
	this.head=new Point3D(head.x.coord/len,head.y.coord/len,head.z.coord/len);
	return this;
}
/**
 * A function retun the normalized vector.
 * @return The normalized vector.
 */
public Vector normalized() 
{
	return new Vector(normalize().head);	
}

/**
 * Function that performs comparison.
 */
@Override
public boolean equals(Object obj) 
{
	if (this == obj)
	{
		return true;
	}
	if (obj == null)
	{
		return false;
	}
	if (getClass() != obj.getClass())
	{
		return false;
	}
	Vector other = (Vector) obj;
	if (head == null) 
	{
		if (other.head != null)
			return false;
	} else if (!head.equals(other.head))
	{
		return false;
	}
	return true;
}
/**
 * ovveride the toString.
 */
@Override
public String toString()
{
	return "Vector=" + head;
}
}
