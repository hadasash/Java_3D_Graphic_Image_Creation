import primitives.*;
import static java.lang.System.out;
import static primitives.Util.*;
//Adi Ashkenazi 322408980 adashken@g.jct.ac.il 
//Hadasa Fox 317801165 hadasash@g.jct.ac.il
/**
 * Test program for the 1st stage
 *
 * @author Adi Ashkenazi && Hadasa Fox
 */
public final class main 
{

    /**
     * Main program to tests initial functionality of the 1st stage
     * 
     * @param args irrelevant here
     */
    public static void main(String[] args) 
    {
    	try 
        { // test zero vector
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException e) {}

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // test length..
        try {
        if (!isZero(v1.lenghtSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");
        if (!isZero(new Vector(0, 3, 4).lenght() - 5))
            out.println("ERROR: length() wrong value");}
        catch(IllegalArgumentException e){}

        // test Dot-Product
        if (!isZero(v1.dotProduct(v3)))
            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            out.println("ERROR: dotProduct() wrong value");

        // test Cross-Product
        try 
        { // test zero vector
            v1.crossProduct(v2);
            out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (IllegalArgumentException e) {}
        Vector vr = v1.crossProduct(v3);
        try {
        if (!isZero(vr.lenght() - v1.lenght() * v3.lenght()))
            out.println("ERROR: crossProduct() wrong result length");
        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
            out.println("ERROR: crossProduct() result is not orthogonal to its operands");
        }catch(IllegalArgumentException e) {}

        // test vector normalization vs vector length and cross-product
        try {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        
        if (vCopy != vCopyNormalize)
            out.println("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.lenght() - 1))
            out.println("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        if (u == v)
            out.println("ERROR: normalizated() function does not create a new vector");
        }catch(IllegalArgumentException e) {}

        // Test operations with points and vectors
        Point3D p1 = new Point3D(1, 2, 3); 
        try {
        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            out.println("ERROR: Point + Vector does not work correctly");
        if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
             out.println("ERROR: Point - Point does not work correctly");
        }catch(IllegalArgumentException e) {}
        out.println("If there were no any other outputs - all tests succeeded!");
    }
}
