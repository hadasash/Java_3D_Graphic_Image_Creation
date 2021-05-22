package elements;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Light source is far away - no attenuation with distance.
 * 
 * @author Adi and Hadasa
 * 
 */
public class DirectionalLight extends Light implements LightSource
{
	private Vector direction;		//directional light source

	/* ********* Constructors ***********/

	/**
	 * a new Directional Light
	 *
	 * @param color color
	 * @param direction light direction
	 */
	public DirectionalLight(Color color, Vector direction) 
	{
		super(color);
		this.direction = direction.normalized();
	}

	/* ************* Getters/Setters *******/
	/**
	 * get light intensity
	 * @param p the point
	 * @return light intensity
	 */
	@Override
	public Color getIntensity(Point3D p) 
	{
		//call to get intensity of "light" interface that doesnt get parameters.
		//because its the same intensity everywhere
		return super.getIntensity();
	}

	/**
	 * get vector from light
	 * @param p the point
	 * @return vector
	 */
	@Override
	public Vector getL(Point3D p) 
	{
		return direction;
	}

    /**
     * get distance of lightsource from point
     * @param p the point
     * @return distance
     */
    @Override
    public double getDistance(Point3D point) 
    {
        return Double.POSITIVE_INFINITY;
    }
}