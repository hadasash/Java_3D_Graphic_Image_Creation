//Adi And Hadasa
package elements;
import primitives.*;
import static primitives.Util.*;
/**
 * class camera- contains it's position and it's vectors.
 * also contains details about the virtual "view plane" which is in front of the camera.
 * responsible for creating the rays to go through the pixels.
 * 
 * @author Adi and Hadasa
 *
 */
public class Camera
{
	//camera:
	private Point3D p0;		//camera's position
	private Vector vTo;		//directed forward to camera
	private Vector vUp;		//directed upwards to camera
	private Vector vRight;	//the right direction of camera

	//view plane:
	private double width;	//the view plane's width
	private double height;	//the view plane's height
	private double distance;//the distance of the camera from the view plane

	//aperture, depth of width:
	private double apWidth;
	private double apHeight;
	
	private double focalPlaneDistance;	//depth of field- the distance from camera to focal plane



	/* ********* Constructor ***********/
	/**
	 * A new Camera
	 * @param _p0 camera location
	 * @param _vUp- the upwards vector
	 * @param _vTo- the forward vector
	 */
	public Camera(Point3D _p0, Vector _vTo, Vector _vUp )
	{
		if(!Util.isZero(_vUp.dotProduct(_vTo)))//if they are orthogonal-the dot product=0. vUp and vTo must be orthogonal
			throw new IllegalArgumentException("Vectors are not orthogonal");

		p0 = new Point3D(_p0.getX(),_p0.getY(),_p0.getZ());//location of camera
		vTo = _vTo.normalized();	//camera's vectors must be normalized
		vUp = _vUp.normalized();	//camera's vectors must be normalized
		vRight = vTo.crossProduct(vUp).normalized();//we used crossProduct, since vRight is the normal to vTo and vUp plane.
	}

	/* ************* Getters/Setters *******/
	/**
	 * @return the position point
	 */
	public Point3D getp0() 
	{
		return p0;
	}

	/**
	 * @return the forward vector
	 */
	public Vector getvTo() 
	{
		return vTo;
	}

	/**
	 * @return the upwards vector
	 */
	public Vector getvUp() 
	{
		return vUp;
	}

	/**
	 * @return the right-directed vector
	 */
	public Vector getvRight() 
	{
		return vRight;
	}

	/**
	 * @return the dis of canera from v-p
	 */
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
	 * @param _width of view plane
	 * @param _height of view plane
	 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Camera setVpSize(double _width, double _height)//set the viewplane's size
	{
		width=_width;
		height=_height;
		return this;
	}

	/**
	 * @param _distance of camera from view plane
	 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Camera setVpDistance(double _distance)//set the distance from the camera to viewPlane
	{
		distance=_distance;
		return this;
	}

	/**
	 * @return apWidth
	 */
	public double getApWidth() 
	{
		return apWidth;
	}

	/**
	 * @param apWidth
	 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Camera setApWidth(double apWidth) 
	{
		this.apWidth = apWidth;
		return this;
	}

	/**
	 * @return apHeight
	 */
	public double getApHeight() 
	{
		return apHeight;
	}

	/**
	 * @param apHeight
	 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Camera setApHeight(double apHeight) 
	{
		this.apHeight = apHeight;
		return this;
	}
	
	/**
	 * set distance of focal plane
	 * @param d
	 * @return the camera itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Camera setFocalPlaneDistance(double d)
	{
		focalPlaneDistance = d;
		return this;
	}
	
	/**
	 * @return focalPlaneDistance
	 */
	public double getFocalPlaneDistance()
	{
		return focalPlaneDistance;
	}

	/* ************* construct ray *******/
	/**
	 * @param nX= number of pixels on tzir x of view plane
	 * @param nY= number of pixels on tzir y of view plane
	 * @param i= row index
	 * @param j= column index
	 * @return the ray that is created from camera to cross the given pixel of view plane
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i)
	{
		if (isZero(distance)) //we assume that distance between camera and view plane must be > than 0.
		{
			throw new IllegalArgumentException("distance cannot be 0");
		}
		Point3D Pc = p0.add(vTo.scale(distance));//Pc is the center point in view plane. exactly in front of camera- vTo.
		//Pc might be in a pixel (if the numbers of pixels are uneven)
		//or between the pixels (if the numbers of pixels are even).
		//we will test the 2 cases in "CameraTests".

		double Ry = height / nY;//the y ratio on view plane
		double Rx = width / nX;	//the x ratio on view plane

		double yi = ((i - nY / 2d) * Ry + Ry / 2d);//calc the y value- the scalar of hazaza on tzir y
		double xj = ((j - nX / 2d) * Rx + Rx / 2d);//calc the x value- the scalar of hazaza on tzir x

		Point3D Pij = Pc;		//start nevigating from the center point on view plane

		//we only have to do hazaza on the tzir when it's not 0. 
		//if the y or x value=0, we don't need to do hazaza in that tzir, vRight (x) or vUp- y.
		if (!isZero(xj)) 
		{
			Pij = Pij.add(vRight.scale(xj));
		}
		if (!isZero(yi)) 
		{
			Pij = Pij.add(vUp.scale(-yi)); //we use -yi and not yi since we start counting the rows (i) 
			//from up to down (like a matrix), opposite to tzir y in reality and to vUp.
		}
		//now Pij is the center of the wanted pixel

		Vector Vij = Pij.subtract(p0);//Vij is the vector from the camera to Pij

		return new Ray(p0, Vij);//create the ray
	}


	/**
	 * Finds the center point of a particular pixel
	 * 
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return the center point
	 */
	public Point3D getCenterOfPixel(int nX, int nY, int j, int i)
	{
		//the center point of the center pixel
		Point3D Pc = new Point3D(p0.add(vTo.scale(distance)).getX(),p0.add(vTo.scale(distance)).getY(),p0.add(vTo.scale(distance)).getZ());
		//width of pixel
		double Rx = width / nX;
		//height of pixel
		double Ry = height / nY;
		double tempY = ((i - nY / 2d) * Ry + Ry/2d);
		double tempX = ((j - nX / 2d) * Rx + Rx/2d);
		Point3D p = new Point3D(Pc.getX(),Pc.getY(),Pc.getZ());
		if(!isZero(tempX))
		{
			Vector Vx = new Vector(vRight.scale(tempX).getHead());
			p = p.add(Vx);
		}
		if(!isZero(tempY))
		{
			Vector Vy = new Vector(vUp.scale(-tempY).getHead());
			p = p.add(Vy);
		}
		return p;
	}

}


