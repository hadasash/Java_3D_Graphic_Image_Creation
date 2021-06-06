package renderer;
import java.util.LinkedList;
import java.util.List;
import elements.*;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Adi and Hadasa
 *
 * The Render- this class contains all the needed details to form and produce a colored image:
 * the scene, the camera, the imageWriter, the rayTracer.
 * it delegates actions to the matching classes, when its needed.
 */
public class Render 
{
	ImageWriter imageWriter;	//creates the image
	//no need for scene here since its in ray tracer base
	//Scene scene;				//contains the geometries
	Camera camera;				//the camera- the viewPlane, the position and vectors
	RayTracerBase rayTracerBase;//in charge of tracing the rays and calculating the pixel's color

	//no constructor//

	/* ************* Setters *******/
	/**
	 * @param imageWriter
	 * @return the Render itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Render setImageWriter(ImageWriter imageWriter) 
	{
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * @param camera
	 * @return the Render itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Render setCamera(Camera camera) 
	{
		this.camera = camera;
		return this;
	}

	/**
	 * @param rayTracerBase
	 * set the scene as well
	 * @return the Render itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Render setRayTracerBase(RayTracerBase rayTracerBase) 
	{
		this.rayTracerBase = rayTracerBase;
		return this;
	}

	/* ************* Rendering-Process functions *******/
	/**
	 * a very important function:
	 * this function moves on all pixels of viewPlane in camera, 
	 * calls to "constructRayThroughPixel" to create a ray for each pixel,
	 * calls to rayTracer's "traceRay" to follow the ray and calculate the pixel's color,
	 * and calls to imageWriter's "writePixel" to paint the pixel on the image.
	 */
	public void renderImage()
	{
		//if one of the needed fields of Render is missing (a house with no walls)- throw exception.
		if(imageWriter==null)
			throw new IllegalArgumentException("no imageWriter");
		if(rayTracerBase==null)
			throw new IllegalArgumentException("no rayTracerBase");
		if(rayTracerBase.scene==null)
			throw new IllegalArgumentException("no scene");
		if(camera==null)
			throw new IllegalArgumentException("no camera");

		//rezolution:
		int Nx = imageWriter.getNx(); //number of pixels on tzir x
		int Ny = imageWriter.getNy(); //number of pixels on tzir y

		for (int row = 0; row < Ny; ++row) //move on all rows of view plane
		{
			for (int column = 0; column < Nx; ++column) //move on all columns of view plane
			{
				Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row); //construct a ray through the pixel
				//imageWriter.writePixel(column, row, rayTracerBase.traceRay(ray));//paint the pixel with the right color
				
				//our specific pixel's center:
				Point3D p=camera.getCenterOfPixel(Nx, Ny, column, row);
				Point3D pixelCenter = new Point3D(p.getX(),p.getY(),p.getZ());

				//if the aperture is not restarted- no improve of depth of field:
				if(camera.getApWidth() == 0 && camera.getApHeight() == 0)
				{
					imageWriter.writePixel(column, row, rayTracerBase.traceRay(ray));//paint the pixel with the right color

				}
				else//only if the aperture is set- do the improve of depth of field
				{
					//find the focal point of the pixel on the focal plane:
					Point3D focalPoint = findFocalPoint(pixelCenter, camera.getFocalPlaneDistance()-camera.getDistance(), ray.getDir().normalized(), camera.getvTo().normalized());

					//mini project part 1:

					//create many rays from the specific pixel, around pixelCenter- many rays instead of one ray
					List<Ray> rays  = (createFocalRays(focalPoint, camera, pixelCenter));
					rays.add(ray);//add the main ray to the list of rays
					imageWriter.writePixel(column, row, rayTracerBase.traceRays(rays));//paint the pixel with the right color

				}
			}
		}
	}

	/**
	 * print a grid above the image- if wanted
	 * @param interval= the wanted space between the printed lines of grid
	 * @param color of grid lines
	 */
	public void printGrid(int interval, Color color)
	{
		if(imageWriter==null)//if the imageWriter field, that creates the image, is missing in Render-
			throw new IllegalArgumentException("no imageWriter");//throw exception.

		//rezolution:
		double rows = imageWriter.getNy();
		double columns = imageWriter.getNx();

		for (int row = 0; row < rows; ++row) //move on all rows
		{
			for (int column = 0; column < columns; ++column) //move on all columns
			{
				if (column % interval == 0 || row % interval == 0) //print the grid in the given interval
				{
					imageWriter.writePixel(column, row, color);	   //paint the pixel with grid color
				}
			}
		}
	}

	/**
	 * the final action of producing the wanted image
	 */
	public void writeToImage() 
	{
		if(imageWriter==null)//if the imageWriter field, that creates the image, is missing in Render-
			throw new IllegalArgumentException("no imageWriter");//throw exception.

		imageWriter.writeToImage();//delegate the writing to image to imageWriter, that deals with it.
	}

	/**
	 * 
	 * @param p
	 * @param dis
	 * @param vTo
	 * @return intersection point in the focal plane
	 */
	private Point3D findFocalPoint(Point3D pScreen, double dis, Vector direction, Vector vTo)
	{
		double t = dis/(direction.dotProduct(vTo));
		Point3D f=pScreen.add(direction.scale(t));
		return new Point3D(f.getX(),f.getY(),f.getZ());//the focal point
	}

	/**
	 * Creates focus rays
	 * 
	 * @param focalPoint
	 * @param camera
	 * @param dis
	 * @param pixelCenter
	 * @return list of rays from the specific pixel to the focal point. 
	 */
	private List<Ray> createFocalRays(Point3D focalPoint, Camera camera, Point3D pixelCenter)
	{
		//we restart new linked lists because of the low running time of adding new items O(1), and we want to add many items:
		List<Ray> beam = new LinkedList<Ray>();	
		List<Point3D> points = new LinkedList<Point3D>();
		
		//find the range of the pixel on x and y :
		double xStart = pixelCenter.getX() - camera.getApWidth() / 2;//start of aperture on tzir x
		double xEnd = pixelCenter.getX() + camera.getApWidth() / 2;	//end of aperture on tzir x
		double yStart = pixelCenter.getY() - camera.getApHeight() / 2;//start of aperture on tzir y
		double yEnd = pixelCenter.getY() + camera.getApHeight() / 2;//end of aperture on tzir x
		
		//the z value of all the points we'll create is equal to z of the pixelCenter on the view plane:
		double z = pixelCenter.getZ();
		//create random 300 points in the pixel:
		for(int i = 0; i < 300; i ++)
		{
			double x = (double) ((Math.random()*(xEnd - xStart + 1)) + xStart);
			double y = (double) ((Math.random()*(yEnd - yStart + 1)) + yStart);
			points.add(new Point3D(x, y, z));//add new point to list
		}

		//add the edges of the pixel to the points list:
		points.add(new Point3D(xEnd, yEnd, z));
		points.add(new Point3D(xEnd, yStart, z));
		points.add(new Point3D(xStart, yEnd, z));
		points.add(new Point3D(xStart, yStart, z));

		//create a ray from each point in the pixel to the focal point:
		for(Point3D point: points)
		{
			beam.add(new Ray(point, new Vector(focalPoint.subtract(point).getHead())));
		}	

		return beam;//beam of rays from the pixel to the focal point of the pixel
	}


}
