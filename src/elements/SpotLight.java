
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
public class SpotLight extends PointLight 
{
    private Vector direction;		// direction of  spot light

    /* ********* Constructors ***********/

    /**
     *ctr with params
     *
     * @param color  color of light
     * @param position  position of  light source
     * @param direction  direction of  light
     */
    public SpotLight(Color color, Point3D position, Vector direction) 
    {
        super(color, position);
        this.direction = direction.normalized();
    }

    /* ************* Getters & setters *******/

    /**
     * get light intensity
     * @param p the point
     * @return the light
     */
    @Override
    public Color getIntensity(Point3D p) 
    {
        double pl = Util.alignZero(direction.dotProduct(getL(p)));
        if (pl <= 0)
            return Color.BLACK;
        return super.getIntensity(p).scale(pl);
    }
    

}