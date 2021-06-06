package scene;
import primitives.*;
import primitives.Color;
import geometries.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import elements.*;

/**
 * this is scene class, it's a PDS= passive data structure,
 * so all the fields are public.
 * @author efrat & esti
 *
 */
public class Scene 
{
	public String name;					//scene's name
	public Color backGround=Color.BLACK;//default color of the background (unless it was changed)
	public AmbientLight ambientLight=new AmbientLight(Color.BLACK,1);	//ambient light of the scene's objects
	public Geometries geometries;		//the geometries that are in the scene

	public List<LightSource> lights;	//the light sources in the scene
	
	
	/*************** constructor *****************/
	/**
	 * restart the name of scene and restarts an empty geometries list.
	 * @param _name
	 */
	public Scene(String _name)
	{
		name=_name;
		geometries= new Geometries();//empty list of geometries
		lights=new LinkedList<LightSource>();//restart empty list of lights
	}
	
	/*************** setters *****************/
	/**
	 * @param geometries
	 * @return the scene itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Scene setGeometries(Geometries geometries) 
	{
		this.geometries = geometries;
		return this;
	}
	
	/**
	 * @param ambientLight
	 * @return the scene itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) 
	{
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * @param backGround
	 * @return the scene itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Scene setBackGround(Color backGround) 
	{
		this.backGround = backGround;
		return this;
	}
	
	/**
	 * @param lights
	 * @return the scene itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Scene setLights(LightSource...lights) 
	{  
        this.lights.addAll(Arrays.asList(lights));
        return this;
	}
	
	
}
