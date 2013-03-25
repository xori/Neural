package feature.map.utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import static feature.map.utilities.U.*;
/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class DataPreProcess {
    
    /**
     * Reads in the list of files given and collects all the colours from all of
     * the pixels. Question 1 in assignment 2.
     * @param files
     * @return
     *      Array of Double's this is to be the input.
     * @throws IOException 
     */
    public static Double [] readColours (File [] files) throws IOException {
        ArrayList<Double[]> rows = new ArrayList();
        BufferedImage   i = null;
        Double []       row;
        Color           temp;
        for(File f : files) {
           i = ImageIO.read(f);
           
           for(int y = 0; y < i.getHeight(); y++) {
               for(int x = 0; x < i.getWidth(); x++){
                   temp = new Color(i.getRGB(x, y));
                   row = new Double[3];
                   row[0] = l( temp.getRed()   )/255.0;
                   row[1] = l( temp.getGreen() )/255.0;
                   row[2] = l( temp.getBlue()  )/255.0;
                   rows.add(row);
               }
           }
        }
        return (Double[]) rows.toArray();
    }
}
