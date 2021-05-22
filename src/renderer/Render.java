package renderer;
import elements.*;
import primitives.Color;
import primitives.Ray;

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
				imageWriter.writePixel(column, row, rayTracerBase.traceRay(ray));//paint the pixel with the right color
				
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
	
	
}
