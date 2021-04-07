// Adi and Hadasa
package geometries;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import java.util.ArrayList;

/**
 *interface of composite- contains a list of geometries that are intersectable.
 *it implements Intersectable interface  to check the intersections with all the geometries.
 * @author Adi and Hadasa
 */
public class Geometries implements Intersectable
{
	private List<Intersectable> geometries; //list of geometries- all implement intersactable interface

	/*************** ctors *****************/
	/**
	 * restart new empty arrayList of geometries
	 */
	public Geometries() 
	{
		super();
		geometries=new ArrayList<Intersectable>();//arrayList  its faster to get into the items-
		//we need to pass on the list of geometries as fast as we can when we get the intersections with them all
	}
	/**
	 * copy ctor.
	 */
	public Geometries(Intersectable...mygeometries) 
	{
		geometries=new ArrayList<Intersectable>();
		for(int i=0;i<mygeometries.length;i++)
			geometries.add(mygeometries[i]);
	}

	/**
	 * adds new geometries to the list
	 */
	public void add(Intersectable...mygeometries) 
	{
		for(int i=0;i<mygeometries.length;i++)
			geometries.add(mygeometries[i]);
	}

	/**
	 * @param ray
	 * @return  list of intersections of the ray with all the geometries in the list. 
	 */
	@Override
	public List<Point3D> findIntersections(Ray ray) 
	{
		List<Point3D> intersections=new ArrayList<Point3D>();
		for(int i=0; i<geometries.size(); i++) 
		{
			if(geometries.get(i).findIntersections(ray)!=null) //if there are intersections to the ray with the specific geomety
			{
				for(int j=0; j<geometries.get(i).findIntersections(ray).size(); j++) //move on all the intersections point with this shape
				{
					intersections.add(geometries.get(i).findIntersections(ray).get(j));//add them to general list of intersections
				}
			}
		}
		if(intersections.size()==0)
			return null;//there are not intersection 
		return intersections;//return the intersections
	}
}
