package primitives;

public class Ray 
{
Point3D p0;
Vector dir;
/**
 *  * A constractor who gets the data on the plane.
 * @param p0 point.
 * @param dir vector.
 */
public Ray(Point3D p0, Vector dir) 
{
	super();
	this.p0 = p0;
	this.dir = dir.normalized();
	
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
	Ray other = (Ray) obj;
	if (dir == null) 
	{
		if (other.dir != null)
			return false;
	} else if (!dir.equals(other.dir))
		return false;
	if (p0 == null) 
	{
		if (other.p0 != null)
		{
			return false;
		}
	} else if (!p0.equals(other.p0))
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
	return "Ray [Point=" + p0 + ", Vector=" + dir + "]";
}

}
