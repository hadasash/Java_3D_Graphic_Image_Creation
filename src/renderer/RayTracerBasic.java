package renderer;

import static primitives.Util.*;
import primitives.Material;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;
import elements.*;

/**
 * Adi and Hadasa
 *
 * this class extends the RayTracerBase and implements the traceRay function.
 * this class in responsible to calculate the right color of the 
 * intersection point with the ray (through the pixel)
 */
public class RayTracerBasic extends RayTracerBase
{
	//a constant for hazaza of the beginning of light rays from object- for shadows
	private static final double DELTA = 0.01;

	//stop conditions for the recursia of reflection and transparency:
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double INITIAL_K = 1.0;

	/**
	 * constructor that gets the scene we will trace and calls the father's constructor to set it.
	 * @param _scene
	 */
	public RayTracerBasic(Scene _scene)
	{
		super(_scene);
	}

	/**
	 * @param ray
	 * @return the color of the pixel that the ray pass through it
	 */
	public Color traceRay(Ray ray)
	{		
		GeoPoint pointAndGeo=findClosestIntersection(ray);
		if(pointAndGeo!=null)					//if there is an intersection point- calc it's color
			return calcColor(pointAndGeo, ray);
		else							//if the ray doesn't intersect anything- return the background color
			return scene.backGround;
	}

	/**
	 * outer calcColor that adds ambient light, and call the inner calcColor
	 * sending level of recursion, and initial mekadem k=1.
	 * @param geopoint
	 * @param ray
	 * @return
	 */
	private Color calcColor(GeoPoint geopoint, Ray ray) 
	{
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
				.add(scene.ambientLight.getIntensity());//here we add the ambient light- once, without the recursion.
	}

	/**
	 * calculates the color of the point that the ray intersect it 
	 * (we already get here the closest intersection point)
	 * @param point
	 * @param the ray
	 * @param level of recursion- goes down each time till it gets to 1
	 * @param k- mekadem of reflection and refraction so far
	 * @return the color 
	 */
	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) 
	{
		//in the recursive calls to calcColor we might get that intersections=null.
		//if so- return black. no adding color and light.
		if(intersection==null)
			return Color.BLACK;

		Color color = intersection.geometry.getEmission();//the color of the object itself
		color = color.add(calcLocalEffects(intersection, ray, level, k));//diffuse, specular, and shadow.
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));//reflection and refraction- may cause recursion
	}

	/**
	 * Adding diffusion/specular calculation, and check shadow
	 * this function calculates the local light effects in phonge model
	 * @param intersection
	 * @param ray
	 * 	 * @param level of recursion- goes down each time till it gets to 1
	 * @param k- mekadem of reflection and refraction so far
	 * @return the color that was calculated
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray, int level, double k) 
	{
		Vector v = ray.getDir ();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0) 
			return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().nShininess;
		double kd = intersection.geometry.getMaterial().kD, ks = intersection.geometry.getMaterial().kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) 
		{
			Vector l = lightSource.getL(intersection.point).normalized(); 
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) 
			{ // sign(nl) == sing(nv) 
				double ktr = transparency(lightSource, l, n, intersection);
				if (ktr * k > MIN_CALC_COLOR_K) 
				{	//it means if there is transparency and its not "atum"-
					//take it in account for calculating how much shadow.
					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);//ktr=transparency- for shadow
;
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),//diffusive
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));//specular
				}
				//if not- it's "atum" and don't calculate the light
			}
		}
		return color;
	}

	/**
	 * calc the global effects- relection and refraction.
	 * this func call "calcColor" in recursion to add more and more global effects.
	 * @param intersection
	 * @param ray
	 * @param level of recursion- goes down each time till it gets to 1
	 * @param k- mekadem of reflection and refraction so far
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray inRay, int level, double k) 
	{
		Vector n = geopoint.geometry.getNormal(geopoint.point);//normal to geometry in point

		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();

		//improve: check the conditions in the beginning
		if (level == 1 || k < MIN_CALC_COLOR_K) //stop recursion when it gets to the min limit
		{
			return color;
		}

		double kr = material.kR, kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) //stop recursion when it gets to the min limit
		{
			Ray reflectedRay = constructReflectedRay(geopoint.point, inRay, n);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) //stop recursion when it gets to the min limit
		{
			Ray refractedRay = constructRefractedRay(geopoint.point, inRay, n);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
	}


	/**
	 * we need this func here ib order to have local findClosestIntersection
	 * without calling the scene's geometries'es findGeoIntersections.... etc.
	 * @param ray
	 * @return the closest intersection or null if there aren't.
	 */
	private GeoPoint findClosestIntersection(Ray ray) 
	{
		if (ray == null) {
			return null;
		}

		GeoPoint closestPoint = null;
		double closestDistance = Double.MAX_VALUE;
		Point3D ray_p0 = ray.getP0();

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;//no intersection

		for (GeoPoint geoPoint : intersections) //select the closest distance
		{
			double distance = ray_p0.distance(geoPoint.point);
			if (distance < closestDistance) 
			{
				closestDistance = distance;
				closestPoint = geoPoint;
			}
		}
		return closestPoint;
	}

	/**
	 * search for shadow shape
	 * @param l from light source to point
	 * @param n the normal
	 * @param geopoint the point
	 * @return if the point is unshaded. it means- if there is no shade on it- it should have light.
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) 
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
		Point3D point = geopoint.point.add(delta);//we add it to the point by the normal direction,
		//in order to avoid case that the point is inside the object and we think the object itself makes a shadow on the point.
		//so we move the point up a little bit.
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)//if there are no intersections with the light ray
			return true;//the point is unshaded- no one makes shadow on the point

		double lightDistance = light.getDistance(geopoint.point);//the distance of the point of geoPoint from the light source
		for (GeoPoint geop : intersections) 
		{
			if(geop.geometry.getMaterial().kT==0.0)//only if the material of the geo is "atum"- it makes shadow
			{
				//if there is an intersection closer to beginning of ray than our intersection
				//of "geopoint" that we got- return false. it means, there is something shadowing our intersection of "geopoint".
				if (Util.alignZero(geop.point.distance(geopoint.point)-lightDistance) <= 0)
					return false;
			}
		}
		return true;
	}

	/**
	 * this function is like unshaded but returns how much shading
	 * @param ls
	 * @param l
	 * @param n
	 * @param geoPoint
	 * @return the transparency 
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) 
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection, n);
		double lightDistance = light.getDistance(geopoint.point); 
		var intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return 1.0;
		
		double ktr = 1.0;//transparency initial
		
		for (GeoPoint gp : intersections) //move on all the geometries in the way
		{
			//if there are geometr between the point to the light- calculate the transparency 
			//in order to know how much shadow there is on the point
			if (Util.alignZero(gp.point.distance(geopoint.point)-lightDistance) <= 0) 
			{
				ktr *= gp.geometry.getMaterial().kT;//add this transparency to ktr
				if (ktr < MIN_CALC_COLOR_K) //stop the loop- shadow "atum", black. very small transparency
					return 0.0;
			}
		}
		return ktr;
	}


	/**
	 * Calculate Specular component of light reflection.
	 *
	 * @param ks         specular component coef
	 * @param l          direction from light to point
	 * @param n          normal to surface at the point
	 * @param v          direction from point of view to point
	 * @param nShininess shininess level
	 * @param ip         light intensity at the point
	 * @return specular component light effect at the point
	 * Finally, the Phong model has a provision for a highlight, or specular, component, which reflects light in a
	 * shiny way. This is defined by [rs,gs,bs](-V.R)^p, where R is the mirror reflection direction vector we discussed
	 * in class (and also used for ray tracing), and where p is a specular power. The higher the value of p, the shinier
	 * the surface.
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color ip) 
	{
		double p = Util.alignZero(nShininess);

		double nl = Util.alignZero(n.dotProduct(l));//the light normal

		Vector R = l.subtract(n.scale(2 * nl)).normalized(); // nl must not be zero!
		double minusVR = -Util.alignZero(R.dotProduct(v));
		if (minusVR <= 0) 
		{
			return Color.BLACK; // view from direction opposite to r vector
		}
		return ip.scale(ks * Math.pow(minusVR, p));
	}

	/**
	 * Calculate Diffusive component of light reflection.
	 *
	 * @param kd diffusive component coef
	 * @param l          direction from light to point
	 * @param n          normal to surface at the point
	 * @param ip light intensity at the point
	 * @return diffusive component of light reflection
	 * The diffuse component is that dot product n•L that we discussed in class. It approximates light, originally
	 * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
	 * surface is paper. In general, you'll also want this to have a non-gray color value, so this term would in general
	 * be a color defined as: [rd,gd,bd](n•L)
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color ip) 
	{
		double nl = Util.alignZero(n.dotProduct(l));//the light normal

		if (nl < 0) 
			nl = -nl;
		return ip.scale(nl * kd);
	}

	/**
	 * this func returns a new ray- the refracted ray comes from the point because of the light- inRay
	 * @param pointGeo
	 * @param inRay
	 * @param n
	 * @return RefractedRay
	 */
	private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) 
	{
		return new Ray(pointGeo, inRay.getDir(), n);
	}

	/**
	 * this func returns a new ray- the reflected ray comes from the point because of the light- inRay
	 * @param pointGeo
	 * @param inRay
	 * @param n
	 * @return ReflectedRay
	 */
	private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) 
	{
		Vector v = inRay.getDir();
		double vn = v.dotProduct(n);

		if (Util.isZero(vn)) 
		{
			return null;
		}
		Vector r = v.subtract(n.scale(2 * vn));
		return new Ray(pointGeo, r, n);
	}

}
