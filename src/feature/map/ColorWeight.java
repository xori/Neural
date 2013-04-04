package feature.map;

import static feature.map.utilities.U.l;
import java.awt.Color;

/**
 * WeightRepresentation class
 * @author Evan Verworn
 */
public class ColorWeight {
    
    /**
     * Returns the RGB color of the given weight. 
     * @param d
     * @return 
     */
    public static int Visulize (Double [] d) {
        return new Color(l(d[0]),l(d[1]),l(d[2])).getRGB();
    }
}
