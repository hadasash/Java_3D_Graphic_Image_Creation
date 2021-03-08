package primitives;

public class Point3D 
{
Coordinate x;
Coordinate y;
Coordinate z;
// A static field contains the zero vector.
public final static Point3D ZERO=new Point3D(0,0,0);
/**
 * constractor with 3 coordinates.
 * @param x x.
 * @param y y.
 * @param z z.
 */
public Point3D(Coordinate x, Coordinate y, Coordinate z) 
{
	super();
	this.x = x;
	this.y = y;
	this.z = z;
}
/**
 * constractor with 3 doubles.
 * @param a a.
 * @param b b.
 * @param c c.
 */
public Point3D(double a,double b,double c){
	x=new Coordinate(a);
	y=new Coordinate(b);
	z=new Coordinate(c);

}
/**
 * A function that receives a point and makes an addition of the two points.
 * @param v the point that the func get.
 * @return Connection of the two points.
 */
public Point3D add(Vector v)
{
	Coordinate cx;		
	Coordinate cy;	
	Coordinate cz;	
	
	cx=new Coordinate(x.coord+v.getHead().x.coord);
	cy=new Coordinate(y.coord+v.getHead().y.coord);
	cz=new Coordinate(z.coord+v.getHead().z.coord);
	
	return new Point3D(cx,cy,cz);
}
/**
 * A function that receives a three-dimensional point and subtracts both points.
 * @param v the point that the func get.
 * @return Subtraction of the two points.
 */
public Vector subtract(Point3D p)
{
	Coordinate cx;		
	Coordinate cy;	
	Coordinate cz;	
	
	cx=new Coordinate(x.coord-p.x.coord);
	cy=new Coordinate(y.coord-p.y.coord);
	cz=new Coordinate(z.coord-p.z.coord);
	
	
	return new Vector(new Point3D(cx,cy,cz));
}
/**
 * A function that receives a point and calculates the root of the distance between the two.
 * @param p point.
 * @return the root of the distance.
 */
public double distanceSquared(Point3D p)
{
	
	Vector v= p.subtract(ZERO);
	return v.dotProduct(v);
}
/**
 * A function that receives a point and calculates the root of the distance between the two.
 * @param p point.
 * @return the distance.
 */
public double distance(Point3D p)
{	
	double sqrt=Math.sqrt(distanceSquared(p));
	return sqrt;
}
/**
 * Function that performs comparison.
 */
@Override
public boolean equals(Object obj) 
{
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
/**
 * ovveride the toString.
 */
@Override
public String toString() 
{
     return "(" + x + "," + y + "," + z + ")";
}
}
