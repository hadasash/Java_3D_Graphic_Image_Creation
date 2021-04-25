package unittests;

import renderer.ImageWriter;

import java.awt.*;

import org.junit.Test;

/**
 * Adi and Hadasa
 *
 * Test method for {@link renderer.ImageWriter#ImageWriter(java.lang.String, int, int)}.
 */
public class ImageWriterTests
{

    @Test
    public void ImageWiterWriteToImageTest()
    {
        ImageWriter imageWriter = new ImageWriter("image_01", 800, 500);
        
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        
        for (int i = 0; i < Ny; i++) //move on all the x in the matrix of pixels
        {
            for (int j = 0; j < Nx; j++) //move on all the  y in the matrix of pixels
            {
                if (i % 50 == 0 || j % 50 == 0)//interval = 50
                {
                	primitives.Color green=new primitives.Color(Color.red);// grid color
                    imageWriter.writePixel(j, i, green);
                }
                else//all  the rest= the background
                {
                	primitives.Color white=new primitives.Color(Color.green);// background color
                    imageWriter.writePixel(j, i, white);
                }
            }
        }
        imageWriter.writeToImage();//write the image
    }
}
