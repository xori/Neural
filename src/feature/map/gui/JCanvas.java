package feature.map.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import static feature.map.gui.Main.*;
import java.awt.Graphics2D;
import java.awt.Image;
/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class JCanvas extends JPanel{
    
    public BufferedImage graph;
    private Graphics2D g;
    
    public JCanvas () {
        graph = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        Graphics g = graph.getGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 300, 300);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(graph, 0, 0, this);
    }
    
    public void setImage(Image img) {
        g = graph.createGraphics();
        g.drawImage(img, 0, 0, null);
    }
}
