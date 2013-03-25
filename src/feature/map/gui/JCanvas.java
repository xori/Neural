package feature.map.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import static feature.map.gui.Main.*;
/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class JCanvas extends JPanel{
    
    public BufferedImage graph;
    
    public JCanvas () {
        int width = window.canvas.getWidth();
        int height = window.canvas.getHeight();
        graph = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(graph, 0, 0, this);
    }
    
}
