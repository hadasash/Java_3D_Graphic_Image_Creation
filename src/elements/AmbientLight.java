package elements;
import primitives.*;

/**
 * class ambient light, the basic light we use
 * @author Adi and Hadasa
 *
 */
public class AmbientLight extends Light
{
	//the color Intensity;
	
	/*************** ctor *****************/
	/**
	 * ctor restarts the Intensity 
	 * @param Ia
	 * @param ka
	 */
	public AmbientLight(Color Ia, double ka)
	{
		super(Ia.scale(ka));// basic light class
	}

}
