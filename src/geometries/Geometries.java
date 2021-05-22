// Adi and Hadasa
package geometries;

import java.util.List;

import primitives.Ray;
import java.util.ArrayList;

/**
 * 
 * @author Adi & Hadasa
 */
public class Geometries implements Intersectable
{
	private List<Intersectable> geometries; //list of geometries- all implement intersactable interface

	/*************** ctors *****************/
	/**
	 * default ctor- restart new empty arrayList of geometries
	 */
	public Geometries() 
	{
		super();
		geometries=new ArrayList<Intersectable>();
		
	}

	/**
	 * copy-ctor. copies the given array of geometries.
	 */
	public Geometries(Intersectable...mygeometries) 
	{
		geometries=new ArrayList<Intersectable>();
		for(int i=0;i<mygeometries.length;i++)
			geometries.add(mygeometries[i]);
	}

	/*************** add *****************/
	/**
	 * function that adds new geometries to the list
	 */
	public void add(Intersectable...mygeometries) 
	{
		for(int i=0;i<mygeometries.length;i++)
			geometries.add(mygeometries[i]);
	}

	/**
	 * @param ray
	 * @return a list of intersections of the ray with all the geometries in the list. all the composite component.
	 *  design pattern of composite
	 */
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) 
	{
		List<GeoPoint> intersections=new ArrayList<GeoPoint>();//new empty list of points and geometries
		for(int i=0; i<geometries.size(); i++) 				 //move on all the geometries
		{
			if(geometries.get(i).findGeoIntersections(ray)!=null) //if there are intersections to the ray with the specific shape
			{
				for(int j=0; j<geometries.get(i).findGeoIntersections(ray).size(); j++) 
				{
					intersections.add(geometries.get(i).findGeoIntersections(ray).get(j));//add them to list of intersections
				}
			}
		}
		if(intersections.size()==0)
			return null;
		return intersections;//return intersections
	}
}
