package renderer;
import primitives.*;
import scene.*;

/**
 * Adi and Hadasa
 *
 * An abstract class whose children must implement the traceRay function that returns the color of the pixel through which the beam passes
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
	 * @return color of the pixel through which the beam passes
	 */
	public abstract Color traceRay(Ray ray);

}
