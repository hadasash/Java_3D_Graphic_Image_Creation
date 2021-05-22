package primitives;

/**
 * 
 * @author Adi and Hadasa
 * this class contains the features of a material- the shinines, kd and ks,
 * all the parameters that affect the light comes from and to this material.
 *
 */
public class Material 
{
	public double kD=0;	//difusive
    public double kS=0;	//specular 
    public int nShininess=0;
    
    public double kT=0.0;//refraction 
    public double kR=0.0;//reflection 
    
    //******************setters****************
    /**
     * set kd
     * @param _kD
     * @return the material itself to use design pattern of builder- to concatenate calls to setters.
     */
	public Material set_kD(double _kD) 
	{
		this.kD = _kD;
		return this;
	}
	/**
	 * set ks
	 * @param _kS
	 * @return the material itself to use design pattern of builder
	 */
	public Material set_kS(double _kS) 
	{
		this.kS = _kS;
		return this;
	}
	/**
	 * set shininess
	 * @param _nShininess
	 * @return the material itself to use design pattern of builder.
	 */
	public Material set_nShininess(int _nShininess) 
	{
		this.nShininess = _nShininess;
		return this;
	}
	/**
	 * set kT
	 * @param kt
	 * @return the material itself to use design pattern of builder.
	 */
	public Material set_kT(double kt) 
	{
		this.kT = kt;
		return this;
	}
    
	/**
	 * set kR
	 * @param kr
	 * @return the material itself to use design pattern of builder.
	 */
	public Material set_kR(double kr) 
	{
		this.kR = kr;
		return this;
	}
}
