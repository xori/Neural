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
            distance += Math.abs( input[i] - weight[i] );
        }
        
        return distance;
    }
    
    
    public void setAttraction (Double [] input, double amount) {
        for(int i = 0; i < weight.length; i++){
            weight[i] = SOFM.LEARNING_RATE * amount * input[i] + (1 - SOFM.LEARNING_RATE * amount) * weight[i];
        }
    }
}
