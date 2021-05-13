package elements;
import primitives.Color;

/**
 * 
 * @author Adi & Hadasa
 *
 */
abstract class Light 
{

	private Color intensity;// intensity 
	

	/* ************* constructors *******/
	/**
	 *  new Light
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
		return intensity;
	}



}


