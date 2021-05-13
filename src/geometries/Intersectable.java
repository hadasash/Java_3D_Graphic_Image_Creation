package geometries;
import java.util.List;
import java.util.stream.Collectors;
import primitives.*;

/**
 * interface of geometry  to findIntersections of ray with the geometry
 * @author Adi and Hadasa
 */
public interface Intersectable 
{
	/**
	 * 
	 * @param ray
	 * @return list of GeoPints intersections and the geometries that are intersected.
	 */
    List<GeoPoint> findGeoIntersections(Ray ray);
	
    /**
     * 
     * @param ray
     * @return list intersection point
     */
    default List<Point3D> findIntersections(Ray ray) 
    {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                               : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }

    
	/**
	 * inner class
	 */
	public static class GeoPoint 
	{
		public Geometry geometry;	// geometry shape
	    public Point3D point;		//intersection point with the geometry
	    
	    /**
	     * ctor
	     * @param geometry
	     * @param point
	     */
	    public GeoPoint(Geometry geometry, Point3D point) 
	    {
			super();
			this.geometry = geometry;
			this.point = point;
		}

	    
		/* ************** Admin *****************/
		@Override
		public String toString() {
			return "GP{" + "G=" + geometry + ", P=" + point + '}';
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}

	}

	
}
