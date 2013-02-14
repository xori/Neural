package neural.net;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Evan Verworn (4582938) <ev09qz@brocku.ca>
 */
public class Node implements Serializable {
    public enum type {
        INPUT, HIDDEN, OUTPUT
    };
    public type NODETYPE;
    public static double LEARNSPEED = 0.2;
    
    private double output = Double.NaN;
    private double error  = Double.NaN;
    private double sum    = Double.NaN;
    private ArrayList<Double> weights;
    private ArrayList<Node> inputs;
    private ArrayList<Node> outputs;
    public Node () {
        weights = new ArrayList<Double>();
        inputs  = new ArrayList<Node>();
        outputs = new ArrayList<Node>();
        NODETYPE = type.HIDDEN;
    }
    
    
    public double output () {
        if (!Double.isNaN(output))
            return output;
        
        sum = 0;
        for(int i = 0; i < inputs.size(); i++)
            sum += inputs.get(i).output() * weights.get(i);
        
        output = Math.tanh(sum);
        return output;        
    }
    
    public double askForAdjustments(Node n) {
        if (!inputs.contains(n)) {
            throw new Error("Node:: Asked for an object that doesn't exist.");
        }
        if (Double.isNaN(error))
            throw new Error("Node:: Adjustment not calculated for this layer. ("
                    +NODETYPE+")");
        
        return error * weights.get(inputs.indexOf(n));
    }
    
    /**
     * This is meant for the output layer
     * @param error 
     */
    public void setError(double error) {
        this.error = error;
    }
    
    public void addLink(Node node) {
        inputs.add(node);
        weights.add(0.1);
    }
    public void addOutputLink(Node node) {
        outputs.add(node);
    }
    
    public void setInputNode (double value){
        output = value;
    }
    
    public void clear () {
        if (NODETYPE != type.INPUT) {
            output = Double.NaN;
        }
        sum     = Double.NaN;
        error   = Double.NaN;
    }
    
    private double differential(double sum) {
        return (4 * Math.pow(Math.cosh(sum),2))/Math.pow(Math.cosh(2* sum)+1, 2);
    }
    
    public void randomize() {
        for (int i = 0; i < weights.size(); i++) {
            weights.add(i, Main.r.nextDouble()*2-1);
            weights.remove(i+1);
        }
    }
    
    public void backprop() {
        if (NODETYPE == Node.type.INPUT || NODETYPE == Node.type.OUTPUT)
            return; // trival case.
        if (Double.isNaN(error))
            error = 0;
        //TODO
        for (Node n : outputs)
            error += n.askForAdjustments(this);
    }
    
    public void forwardprop () {
        if (NODETYPE == type.INPUT)
            return;
        if (Double.isNaN(error) || Double.isNaN(sum))
            throw new Error("Node:: Error not set. Did you run backprop first? ("
                    +NODETYPE+")");
        
        for(int i = 0; i < inputs.size(); i++) {
            weights.add(i,
                    weights.get(i) + LEARNSPEED * error * 
                    differential(sum) * inputs.get(i).output()
            );
            weights.remove(i+1);
        }
    }
}
