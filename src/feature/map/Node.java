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
     *  Create a new node with <i>n</i> Weights init to a random value
     *  between 0 and 1;
     * @param nWeights 
     */
    public Node (int nWeights) {
        weight = new Double[nWeights];
        for(int i = 0; i < weight.length; i++){
            weight[i] = Main.gen.nextDouble();
            if (Double.isNaN(weight[i])) {
                System.exit(99);
            }
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
    public Double getDistance(int x, int y, Double [] input) {
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
        if (Double.isNaN(distance)){
            e(weight[0]);
            System.exit(4);         
        }
        return Math.sqrt(distance);
    }
    
    /**
     * Weight update function, as per the given lecture material.
     * @param input
     *          target weights
     * @param amount 
     *          should be a value between 0 and 1
     */
    public double setAttraction (Double [] input, double amount) {
        double out = 0;
        double temp;
        if (Double.isNaN(amount))
            System.exit(2);
        if (Double.isNaN(input[0]) || Double.isNaN(input[1]) || Double.isNaN(input[2]) ) {
            o("Before");
            System.exit(1);
        }
        
        for(int i = 0; i < weight.length; i++){
            temp = (input[i] - weight[i]) * SOFM.LEARNING_RATE * amount;
            //temp = SOFM.LEARNING_RATE * amount * input[i] + (1 - SOFM.LEARNING_RATE * amount);
            out += temp;
            weight[i] = temp + weight[i];
            
            if(weight[i]< 0.0001) {
                weight[i] = 0.0;
            } else if(weight[i] > 0.999) {
                weight[i] = 1.0;
            }
        }
        
        if (Double.isNaN(input[0]) || Double.isNaN(input[1]) || Double.isNaN(input[2]) ) {
            o("After");
            System.exit(1);
        }
        
        return out;
    }
}
