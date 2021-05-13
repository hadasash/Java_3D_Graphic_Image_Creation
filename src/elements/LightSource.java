package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * 
 * @author Adi & Hadasa
 */

public interface LightSource 
{
        /**
         * get Color Intensity of  point
         *
         * @return color Intensity
         */
        Color getIntensity(Point3D p);

        /**
         * get the direction of the light to a point
         *
         * @param p the point
         * @return normalized vector
         */
        Vector getL(Point3D p);
        
}