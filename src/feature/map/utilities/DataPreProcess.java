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
                   row[0] = l1( temp.getRed()   );
                   row[1] = l1( temp.getGreen() );
                   row[2] = l1( temp.getBlue()  );
                   rows.add(row);
               }
           }
        }
        return (Double[]) rows.toArray();
    }
    
    public static Double[][] random8Colours() {
        Color[] c = new Color[] {
            //Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, 
            Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
            Color.RED, Color.WHITE, Color.YELLOW
        };
        ArrayList<Double[]> list = new ArrayList<Double[]>();
        Double [] temp;
        for(Color C : c){
            temp = new Double[]{
              l1(C.getRed()), l1(C.getGreen()), l1(C.getBlue())
            };
            list.add(temp);
        }
        return list.toArray(new Double[0][0]);
    }
}
