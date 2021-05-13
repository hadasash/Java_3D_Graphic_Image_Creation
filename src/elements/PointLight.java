package elements;
import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * 
 * 
 * @author Adi & Hadasa
 */
public class PointLight extends Light implements LightSource 
{
	private Point3D position;		//the position of the point light
	private double Kc=1;				
	private double Kl=0;				
	private double Kq=0;				
	/* ********* Constructors ***********/

	/**
	 * ctr
	 * @param color the color of the light
	 * @param position the position 
	 */
	public PointLight(Color color, Point3D position) 
	{
		super(color);
		this.position = new Point3D(position.getX(),position.getY(),position.getZ());
	}

	/* ************* Getters & setters *******/
	/**
	 * get intensity
	 * @param p the point
	 * @return the light
	 */
	@Override
	public Color getIntensity(Point3D p) 
	{
		double distance2 = Util.alignZero(p.distanceSquared(position));
		double distance = Util.alignZero(p.distance(position));
		return getIntensity().scale(1/(Kc +Kl*distance + Kq*distance2));
	}

	/**
	 * get vector from light
	 * @param p the point
	 * @return the vector
	 */
	@Override
	public Vector getL(Point3D p)
	{
		if (p.equals(position)) 
			return null;
		return p.subtract(position).normalized();
	}

	/**
	 * Get light position
	 * @return point
	 */
	public Point3D getPosition() 
	{
		return position;
	}

	/**
	 * set Kc 
	 * @param Kc
	 * @return the PointLight itself for design pattern of builder
	 */
	public PointLight setKc(double Kc) 
	{
		this.Kc=Kc;
		return this;
	}

	/**
	 * set kl 
	 * @param kl
	 * @return the PointLight itself for design pattern of builder
	 */
	public PointLight setKl(double kl) 
	{
		this.Kl=kl;
		return this;
	}

	/**
	 * set Kq 
	 * @param Kq
	 * @return the PointLight itself for design pattern of builder
	 */
	public PointLight setKq(double Kq) 
	{
		this.Kq=Kq;
		return this;
	}

}