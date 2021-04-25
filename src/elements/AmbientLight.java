package elements;
import primitives.*;

/**
 * @author Adi Ashkenazi & Hadasa Fox
 *
 */
public class AmbientLight 
{
	Color Intensity;
	
	/*************** ctor *****************/
	/**
	 * ctor that calculat intensity
	 * @param Ia
	 * @param ka
	 */
	public AmbientLight(Color Ia, double ka)
	{
		Intensity=Ia.scale(ka);
	}

	/*************** get *****************/
	/**
	 * @return  Intensity
	 */
	public Color getIntensity()
	{
		return Intensity;
	}
}
