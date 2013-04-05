package feature.map;

import feature.map.utilities.Pnt;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static feature.map.gui.Main.window;
import static feature.map.utilities.U.*;
import java.awt.Color;

/**
 *  The class that organizes the SOFM. This trains and administers it.
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class SOFM extends Thread{
    public static int WIDTH, HEIGHT, GENERATIONS;
    public static double NB_HOOD, S_NB_HOOD, LEARNING_RATE, S_LEARNING_RATE;;
    
    public Node [][] map;
    
    public List<Double[]>   data;
    public List<Epoch>      output;
    
    /**
     * @param inputFeatures 
     *      number of dimensions of the input vector.
     * @param width
     * @param height
     *      of the map
     * @param learning
     *      starting learning rate constant
     * @param neighbourhood 
     *      the starting size of the neighbourhood update
     */
    public SOFM (int inputFeatures, int width, int height, double learning, 
            int neighbourhood, int generations) {
        WIDTH = width;
        HEIGHT = height;
        S_NB_HOOD = NB_HOOD = neighbourhood;
        S_LEARNING_RATE = LEARNING_RATE = learning;
        GENERATIONS = generations;
        
        map = new Node[WIDTH][HEIGHT];
        for(int y = 0; y < HEIGHT; y++)
            for(int x = 0; x < WIDTH; x++) {
                map[x][y] = new Node(inputFeatures);
            }
        
        // Set with .setData()
        data = null;
        
        output = new ArrayList<Epoch>();
    }
    
    /**
     * Performs a linear euclidian distance function
     * @param p
     * @param q
     * @return 
     *      double between 0 and 1;
     */
    public double distance(Pnt p, Pnt q) {
        // sqrt(min(|x1 - x2|, w - |x1 - x2|)^2 + min(|y1 - y2|, h - |y1-y2|)^2)
        double d = Math.sqrt(Math.pow(Math.min(Math.abs(p.x-q.x),WIDTH-Math.abs(p.x-q.x)),2) +
                Math.pow(Math.min(Math.abs(p.y-q.y),HEIGHT-Math.abs(p.y-q.y)),2));
        return NB_HOOD / (d+NB_HOOD);
    }
    
    /**
     * Exponentially decays the neighbourhood update region and the learning 
     *  rate.
     * @param epoch
     *      decay is based on 
     */
    public void decayConstants (int epoch) {
        // Severity of decay. Lower = Faster
        double DROP = GENERATIONS/5;
        NB_HOOD = S_NB_HOOD * Math.exp(-epoch/DROP) + 1;
        //LEARNING_RATE = S_LEARNING_RATE * Math.exp(-epoch/DROP) + 0.01;        
    }
    
    /**
     * Setter function, for use with the DataPreProcess class.
     * @param data 
     */
    public void setData(Double [][] data) {
        this.data = new ArrayList(Arrays.asList(data));
    }
    
    /**
     * The GUI should spawn a new thread to do the actual calculations, hence
     *  a run function.
     */
    @Override
    public void run() {
        // shows convergence
        double  movement;
        Node    minimum;
        Pnt     update_pnt;
        int     tx,ty;
        BufferedImage   testing = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        // Up to the maximum GENERATIONS
        for(int i = 0; i < GENERATIONS; i++) {
            // GUI
            window.jProgressBar.setValue((int)((i/(double)GENERATIONS)*100));
            
            movement = 0;
            // For every line of input
            for(Double[] d : data) {
                
                minimum = map[0][0];
                tx = ty = 0;
                // Find the node with resemblance to the input
                for(int y = 0; y < HEIGHT; y++)
                    for(int x = 0; x < WIDTH; x++){
                        if(map[x][y].getDistance(x,y,d) < minimum.getDistance(x,y,d)) {
                            minimum = map[x][y];
                            tx = x;
                            ty = y;
                        }                        
                    }
                
                // and update it's weights along with it's neighbours
                update_pnt = new Pnt(tx,ty);
                for(int y = 0; y < HEIGHT; y++)
                    for(int x = 0; x < WIDTH; x++){
                        if(distance(new Pnt(x,y),update_pnt) > 0.0001) {
                            movement += Math.abs(map[x][y].setAttraction(d, distance(new Pnt(x,y),update_pnt)));
                        }                        
                    }
            }
            decayConstants(i);
            
            for(int y = 0; y < HEIGHT; y++)
                for(int x = 0; x < WIDTH; x++){
                    testing.setRGB(x, y, ColorWeight.Visulize(map[x][y].weight));
                }
            output.add(new Epoch(testing,movement,map));
            
            if(movement < 0.0001) {
                return;
            }
        }
        window.jProgressBar.setValue(0);
        
    }
    
    private Double[][][] copyOf3Dim(Node [][] array) {
        Double[][][] copy;
        copy = new Double[array.length][array[0].length][];
        for(int y = 0; y < array.length; y++){
            for(int x = 0; x < array[0].length; x++){
                System.arraycopy(array[x][y].weight, 0, copy[x][y], 0, array[x][y].weight.length);
            }
        }
        return copy;
    }    
}
