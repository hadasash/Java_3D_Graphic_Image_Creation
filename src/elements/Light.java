package elements;
import primitives.Color;

/**
 * 
 * this interface matchas ambient light and directional light
 * 
 * @author Adi and Hadasa
 *
 */
abstract class Light 
{

	private Color intensity;// intensity of the light
	

	/* ************* constructors *******/
	/**
	 * A new Light
	 * @param color the color
	 */
	protected Light(Color color)
	{
		intensity = new Color(color);
	}

	/* ************* Getters *******/
	/**
	 * get Color Intensity
	 * @return color Intensity
	 */
	public Color getIntensity() 
	{
		return intensity;// returns the same intensity always no matter where.
	}



}


