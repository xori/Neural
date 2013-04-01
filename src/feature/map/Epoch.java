package feature.map;

import java.awt.Image;

/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class Epoch {
    public double error;
    public Image image;
    public Double [][][] map;
    
    public Epoch(Image i, double e) {
        error = e;
        image = i;
    }
}
