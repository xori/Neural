package feature.map;

import feature.map.utilities.Pnt;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            for(int x = 0; x < WIDTH; x++)
                map[x][y] = new Node(inputFeatures);
        
        // Set with .setData()
        data = null;
        
        output = new ArrayList<Epoch>();
    }
    
    /**
     * Performs a linear eucledian distance function
     * @param p
     * @param q
     * @return 
     *      double between 0 and 1;
     */
    public double distance(Pnt p, Pnt q) {
        double d = Math.sqrt(Math.pow(p.x-q.x,2) + Math.pow(p.y-q.y,2));
        return d / NB_HOOD;
    }
    
    /**
     * Exponentially decays the neighbourhood update region and the learning 
     *  rate.
     * @param epoch
     *      decay is based on 
     */
    public void decayConstants (int epoch) {
        // Severity of decay. Lower = Faster
        double DROP = GENERATIONS;
        NB_HOOD = S_NB_HOOD * Math.exp(-epoch/DROP);
        LEARNING_RATE = S_LEARNING_RATE * Math.exp(-epoch/DROP);
    }
    
    /**
     * Setter function, for use with the DataPreProcess class.
     * @param data 
     */
    public void setData(Double [] data) {
        this.data = new ArrayList(Arrays.asList(data));
    }
    
    /**
     * The GUI should spawn a new thread to do the actual calculations, hence
     *  a run function.
     */
    public void run() {
        for(int y = 0; y < HEIGHT; y++)
            for(int x = 0; x < WIDTH; x++){
                
                
                
            }
    }
}
