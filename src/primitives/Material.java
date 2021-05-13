package primitives;

/**
 * 
 * @author Adi & Hadasa
 *  class has the shinines, kd and ks, and all the parameters that affect the light comes from and to this material.
 *
 */
public class Material 
{
	public double kD=0;	//difusive 
    public double kS=0;	//specular 
    public int nShininess=0;//shininess 
    
    //******************setters****************
    /**
     * set kd
     * @param _kD
     * @return the material itself for design pattern of builder.
     */
	public Material set_kD(double _kD) 
	{
		this.kD = _kD;
		return this;
	}
	/**
	 * set ks
	 * @param _kS
	 * @return the material itself for design pattern of builder
	 */
	public Material set_kS(double _kS) 
	{
		this.kS = _kS;
		return this;
	}
	/**
	 * set shininess
	 * @param _nShininess
	 * @return the material itself for design pattern of builder
	 */
	public Material set_nShininess(int _nShininess) 
	{
		this.nShininess = _nShininess;
		return this;
	}
    
    
}
