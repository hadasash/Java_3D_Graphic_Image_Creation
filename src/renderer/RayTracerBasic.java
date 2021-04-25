package renderer;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * Adi and Hadasa
 *
 *  calculate the right color of the 
 * intersection point with the ray
 */
public class RayTracerBasic extends RayTracerBase
{
	/**
	 * constructor 
	 * @param _scene
	 */
	public RayTracerBasic(Scene _scene)
	{
		super(_scene);
	}
	
	/**
	 * @param ray
	 * @return color of the pixel through which the beam passes
	 */
	public Color traceRay(Ray ray)
	{
		Point3D point=ray.findClosestPoint(scene.geometries.findIntersections(ray));
		if(point!=null)					//calc the color if there is intersect
			return calcColor(point);
		else							//else return the background color
			return scene.backGround;
	}
	
	/**
	 * calculates the color of the point that the ray intersect it
	 * @param point
	 * @return the color 
	 */
	public Color calcColor(Point3D point)
	{
		return scene.ambientLight.getIntensity();//for now- the basic color, background

	}
	
}
