// Adi and Hadasa
package geometries;
import primitives.*;


public class Triangle extends Polygon
{
	/**
	 * constractor with 3 Point3D.
	 * @param p1 point one.
	 * @param p2 point two.
	 * @param p3 point three.
	 */
public Triangle(Point3D p1,Point3D p2,Point3D p3) 
{
	super(p1,p2,p3);
}
/**
 * get point1.
 * @return point one.
 */
public Point3D getPoint1() 
{
    return super.vertices.get(0);
}
/**
 * get point2.
 * @return point two.
 */
public Point3D getPoint2() 
{
    return super.vertices.get(1);
}
/**
 * get point3.
 * @return point three.
 */
public Point3D getPoint3() 
{
    return super.vertices.get(2);
}
/**
 * ovveride the toString.
 */
@Override
public String toString() 
{
    return "Tri{" +
            "P1=" + getPoint1() +
            ", P2=" + getPoint2() +
            ", P3=" + getPoint3() +
            '}' ;
}
}
