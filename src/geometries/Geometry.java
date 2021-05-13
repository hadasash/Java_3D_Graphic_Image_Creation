 // Adi and Hadasa
package geometries;
import primitives.*;

import primitives.Point3D;
import primitives.Vector;

/**
 * interface of geometry that has to implement getNormal. it extends intersectable- the successors have to implement intersections method.
 * @author Adi & Hadasa
 */
public abstract class Geometry implements Intersectable
{
	protected Color emission=Color.BLACK;//the own color of the geometry
	private Material material=new Material();//the material of the geometry- with special features.
	/**********getters and setters********************
	/**
	 * @return the material
	 */
	public Material getMaterial()
	{
		return material;
	}

	/**
	 * @return the emission light
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * set emission and return the object itself
	 * @param emission
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
		
	}
	/**
	 * set material and return the object itself
	 * @param emission
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
		
	}

	/**
	 * @param point
	 * @return the normal vector
	 */
	public abstract Vector getNormal(Point3D point);
}
