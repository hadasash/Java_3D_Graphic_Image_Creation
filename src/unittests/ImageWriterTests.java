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
        ImageWriter imageWriter = new ImageWriter("image_01", 800, 500);//a new image-writer
        //rezolution:
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        
        for (int i = 0; i < Ny; i++) //move on all the x -im
        {
            for (int j = 0; j < Nx; j++) //move on all the  y -im
            {
                if (i % 50 == 0 || j % 50 == 0)//spaces for the grid 
                {
                	primitives.Color purple=new primitives.Color(Color.RED);//the grid color
                    imageWriter.writePixel(j, i, purple);
                }
                else// the background
                {
                	primitives.Color white=new primitives.Color(Color.WHITE);//the background color
                    imageWriter.writePixel(j, i, white);
                }
            }
        }
        imageWriter.writeToImage();//create the image
    }
}
