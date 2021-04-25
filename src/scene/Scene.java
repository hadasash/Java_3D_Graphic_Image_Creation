package scene;
import primitives.*;
import geometries.*;
import elements.*;

/**
 * this is scene class, it's a PDS= passive data structure,
 * so all the fields are public.
 * @author Adi and Hadasa
 *
 */
public class Scene 
{
	public String name;					//the name of scene
	public Color backGround;			
	public AmbientLight ambientLight;	
	public Geometries geometries;		

	/*************** constructor *****************/
	/**
	 * ctr
	 * @param _name
	 */
	public Scene(String _name)
	{
		name=_name;
		geometries= new Geometries();//empty list 
	}
	
	/*************** setters *****************/
	/**
	 * @param geometries
	 * @return the scene itself to use in design pattern of builder
	 */
	public Scene setGeometries(Geometries geometries) 
	{
		this.geometries = geometries;
		return this;
	}
	
	/**
	 * @param ambientLight
	 * @return the scene itself to use in design pattern of builder
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) 
	{
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * @param backGround
	 * @return the scene itself to use in design pattern of builder
	 */
	public Scene setBackGround(Color backGround) 
	{
		this.backGround = backGround;
		return this;
	}
}
