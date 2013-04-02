package feature.map;

import java.awt.image.BufferedImage;

/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class Epoch {
    public double error;
    public BufferedImage image;
    public Node [][] map;
    
    public Epoch(BufferedImage i, double e,Node [][] m) {
        error = e;
        image = i;
        map   = m;
    }
}
