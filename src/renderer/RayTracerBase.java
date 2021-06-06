package renderer;
import java.util.List;
import primitives.*;
import scene.*;

/**
 * Adi and Hadasa
 *
 * its an abstract class, the bace for the ray tracer. the sons must implement the function traceRay 
 * that return the color of the pixel that the ray pass through it.
 */
public abstract class RayTracerBase 
{
	protected Scene scene;//the scene we will trace
	
	/**
	 * constructor that gets the scene
	 * @param _scene
	 */
	public RayTracerBase(Scene _scene)
	{
		scene=_scene;
	}
	
	/**
	 * abstract method
	 * @param ray
	 * @return the color of the pixel that the ray pass through it
	 */
	public abstract Color traceRay(Ray ray);
	
	/**
	 * abstract method
	 * @param list of rays
	 * @return the color of the pixel that the rays pass through it- the average color
	 */
	public abstract Color traceRays(List<Ray> rays);

}
