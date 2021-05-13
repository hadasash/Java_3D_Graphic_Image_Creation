package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * 
 * Adi & Hadasa
 * 
 */
public class DirectionalLight extends Light implements LightSource
{
	private Vector direction;		// directional light source

	/* ********* Constructors ***********/

	/**
	 *ctr
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
	 * get intensity
	 * @param p the point
	 * @return light intensity
	 */
	@Override
	public Color getIntensity(Point3D p) {
		return super.getIntensity();
	}

	/**
	 * get vector light
	 * @param p the point
	 * @return vector
	 */
	@Override
	public Vector getL(Point3D p) 
	{
		return direction;
	}

}