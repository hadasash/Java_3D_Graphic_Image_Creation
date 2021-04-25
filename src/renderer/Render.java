package renderer;
import scene.*;
import elements.*;
import primitives.Color;
import primitives.Ray;

/**
 * Adi and Hadasa
 *
 * Gets all the variables needed to create the color for the image
 */
public class Render 
{
	ImageWriter imageWriter;	
	Scene scene;				
	Camera camera;			
	RayTracerBase rayTracerBase;
	

	
	/* ************* Setters *******/
	/**
	 * @param imageWriter
	 * @return the Render itself to use in design pattern of builder
	 */
	public Render setImageWriter(ImageWriter imageWriter) 
	{
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * @param scene
	 * @return the Render itself to use in design pattern of builder
	 */
	public Render setScene(Scene scene) 
	{
		this.scene = scene;
		return this;
	}

	/**
	 * @param camera
	 * @return the Render itself to use in design pattern of builder
	 */
	public Render setCamera(Camera camera) 
	{
		this.camera = camera;
		return this;
	}

	/**
	 * @param rayTracerBase
	 * @return the Render itself to use in design pattern of builder
	 */
	public Render setRayTracerBase(RayTracerBase rayTracerBase) 
	{
		this.rayTracerBase = rayTracerBase;
		return this;
	}
	
	/* ************* Rendering-Process functions *******/
	/**
	 *   a function that  moves on all pixels and calls to "constructRayThroughPixel" to create a ray for each pixel.
	 *   "traceRay" follow the ray and calculate the pixel's color.
	 *   imageWriter's "writePixel"  paint the pixel .
	 */
	public void renderImage()
	{
		// If one of the  fields is null throws an exception .
		if(imageWriter==null)
			throw new IllegalArgumentException("no imageWriter");
		if(scene==null)
			throw new IllegalArgumentException("no scene");
		if(camera==null)
			throw new IllegalArgumentException("no camera");
		if(rayTracerBase==null)
			throw new IllegalArgumentException("no rayTracerBase");
		
		
		int Nx = imageWriter.getNx(); //number pixels on x
		int Ny = imageWriter.getNy(); //number pixels on y

		for (int row = 0; row < Ny; ++row) 
		{
			for (int column = 0; column < Nx; ++column) 
			{
				Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row); //The ray that comes out of this pixel
				imageWriter.writePixel(column, row,rayTracerBase.traceRay(ray));//paint the pixel
				
			}
		}
	}
	
	/**
	 * A function that draws the grid
	 * @param interval
	 * @param color 
	 */
	public void printGrid(int interval, Color color)
	{
		if(imageWriter==null)//if the imageWriter  missing in Render- exception.
			throw new IllegalArgumentException("no imageWriter");
		
		
		double rows = imageWriter.getNy();
		double columns = imageWriter.getNx();

		for (int row = 0; row < rows; ++row) 
		{
			for (int column = 0; column < columns; ++column) 
			{
				if (column % interval == 0 || row % interval == 0) //if the modulu with the interval =0 -paint
				{
					imageWriter.writePixel(column, row, color);	   //paint the pixel
				}
			}
		}
	}
	
	/**
	 * write to image is the last function to create the image
	 */
	public void writeToImage() 
	{
		if(imageWriter==null)//if the imageWriter  missing in Render- exception.
			throw new IllegalArgumentException("no imageWriter");
		
		imageWriter.writeToImage();//paint the pixels an image
	}
	
	
}
