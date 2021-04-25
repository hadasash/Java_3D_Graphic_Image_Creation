//Adi And Hadasa
package elements;
import primitives.*;
import static primitives.Util.*;

public class Camera
{
	//Variables related to camera location
	private Point3D p0;		
	private Vector vTo;		
	private Vector vUp;		
	private Vector vRight;	

	//Variables related to the view plane: size and distance.
	private double width;	
	private double height;	
	private double distance;

	/* ********* Constructor ***********/
	/**
	 *Constructor who gets parameters
	 * @param _p0 camera location
	 * @param _vUp- Vector direction up
	 * @param _vTo- Vector direction down
	 */
	public Camera(Point3D _p0, Vector _vTo, Vector _vUp )
	{
		if(!Util.isZero(_vUp.dotProduct(_vTo)))//If the vector up and down direction is not orthogonal - an anomaly is thrown
			throw new IllegalArgumentException("Vectors are not orthogonal");
		
		p0 = new Point3D(_p0.getX(),_p0.getY(),_p0.getZ());//location  camera
		vTo = _vTo.normalized();	//Vector normalization
		vUp = _vUp.normalized();	//Vector normalization
		vRight = vTo.crossProduct(vUp).normalized();//Vector product of the two vectors + normalization.
	}

	/* ************* Getters/Setters *******/
	/**
	 * @return location point
	 */
	public Point3D getp0() 
	{
		return p0;
	}
	
	/**
	 * @return Vector direction down
	 */
	public Vector getvTo() 
	{
		return vTo;
	}

	/**
	 * @return Vector direction up
	 */
	public Vector getvUp() 
	{
		return vUp;
	}

	/**
	 * @return right vector
	 */
	public Vector getvRight() 
	{
		return vRight;
	}
	public double getDistance() 
	{
		return distance;
	}
	/**
	 * @return the width of v-p
	 */
	public double getwidth() 
	{
		return width;
	}
	/**
	 * @return the height of v-p
	 */
	public double getheight() 
	{
		return height;
	}

	/**
	 * @param _width view plane
	 * @param _height view plane
	 * @return the camera
	 */
	public Camera setVpSize(double _width, double _height)
	{
		width=_width;
		height=_height;
		return this;
	}

	/**
	 * @param _distance from the view plane
	 * @return the camera 
	 */
	public Camera setVpDistance(double _distance)
	{
		distance=_distance;
		return this;
	}

	/* ************* construct ray *******/
	/**
	 * @param nX= The number of pixels on the x-axis
	 * @param nY= The number of pixels on the y-axis
	 * @param i= index of the row
	 * @param j= index of the colum
	 * @return the ray that  created from the camera 
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i)
	{
		if (isZero(distance)) //The distance must be greater than 0, otherwise you will throw a deviation.
		{
			throw new IllegalArgumentException("distance cannot be zero");
		}
		Point3D Pc = p0.add(vTo.scale(distance));//Pc is the center point in front of camera- vTo ,this point can be in a pixel orbetween the pixels
		
		double Ry = height / nY;
		double Rx = width / nX;	

		double yi = ((i - nY / 2d) * Ry + Ry / 2d);//calc of the y value
		double xj = ((j - nX / 2d) * Rx + Rx / 2d);// calc of x value

		Point3D Pij = Pc;		//start  from the center point.

		 //If X or Y are equal to zero there is no need to move
		if (!isZero(xj))
		{
			Pij = Pij.add(vRight.scale(xj));
		}
		if (!isZero(yi)) 
		{
			Pij = Pij.add(vUp.scale(-yi)); 
			
		}
		
		Vector Vij = Pij.subtract(p0);//vector Vij = Pij-p0

		return new Ray(p0, Vij);//new ray
	}


}



