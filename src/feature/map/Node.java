package feature.map;

import feature.map.gui.Main;
import static feature.map.utilities.U.*;
/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class Node {
    
    Double [] weight;
    
    /**
     * 
     * @param nWeights 
     */
    public Node (int nWeights) {
        weight = new Double[nWeights];
        for(int i = 0; i < weight.length; i++){
            weight[i] = Main.gen.nextDouble();
        }
    }
    
    /**
     * Will use the set distance metric to find the distance between the
     * input vector and the weight vector.
     * @param input 
     *      input vector to compare to
     * @return 
     *      distance value from weight vector
     */
    public Double getDistance(Double [] input) {
        if (input.length != weight.length) {
            e("Node: input length != weight length");
            e(weight);
            e(input);
            System.exit(1);
        }
        
        Double distance = 0.0;
        
        for(int i = 0; i < input.length; i++) {
            distance += Math.pow( input[i] - weight[i],2);
        }
        
        return Math.sqrt(distance);
    }
    
    /**
     * Weight update function, as per the givin lecture material.
     * @param input
     *          target weights
     * @param amount 
     *          should be a value between 0 and 1
     */
    public double setAttraction (Double [] input, double amount) {
        double out = 0;
        double temp = 0;
        for(int i = 0; i < weight.length; i++){
            temp = SOFM.LEARNING_RATE * amount * input[i] + (1 - SOFM.LEARNING_RATE * amount);
            out += temp;
            weight[i] = temp * weight[i];
            
            
            
            
            if(weight[i]< 0.000001) {
                weight[i] = 0.0;
            } else if(weight[i] > 1) {
                weight[i] = 1.0;
            }
            
        }
        return out;
    }
}
