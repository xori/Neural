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
    public type     NODETYPE;
    private System sys;
    private double output = Double.NaN;
    private double error  = Double.NaN;
    private double sum    = Double.NaN;
    private ArrayList<Double> weights;
    private ArrayList<Node> inputs;
    private ArrayList<Node> outputs;
    private ArrayList<Double> momentum;
    
    public Node (System s) {
        weights = new ArrayList<Double>();
        inputs  = new ArrayList<Node>();
        outputs = new ArrayList<Node>();
        momentum= new ArrayList<Double>();
        NODETYPE = type.HIDDEN;
        sys = s;
    }
    
    public double output () {
        if (!Double.isNaN(output)) {
            return output;
        }
        sum = 0;
        for(int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i).output() * weights.get(i);
        }
        output = activation(sum);
        return output;        
    }
    /**
     * Squashes the input value to something between -1 and 1. If ACTIVATION is
     * set to false will use the sigmoid function (which returns a value between
     * 0 and 1) and if it's true will return a value between -1 and 1 as tanh
     * @param sum the weighted sum
     * @return squashed value
     */
    public double activation (double sum) {
        return (sys.ACTIVATION) ? Math.tanh(sum) : 1.0/( 1.0 + Math.pow(Math.E,(-1*sum)));
    }
    
    public double askForAdjustments(Node n) {
        if (!inputs.contains(n)) {
            throw new Error("Node:: Asked for an object that doesn't exist.");
        }
        if (Double.isNaN(error)) {
            throw new Error("Node:: Adjustment not calculated for this layer. ("
                    +NODETYPE+")");
        }
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
        momentum.add(0.0);
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
        for(int i = 0; i < momentum.size(); i++) {
            momentum.set(i, 0.0);
        }
    }
    /**
     * Depending on ACTIVATION will depend on whether is uses the sigmoid 
     * differential or the tanh. 
     * @param x the squashed value.
     * @return 
     */
    private double differential(double x) {
        return 1.0 - (sys.ACTIVATION ? 
                Math.pow(Math.tanh(x),2) : 1.0/( 1.0 + Math.pow(Math.E,(-1*x))));
    }
    
    public void randomize() {
        for (int i = 0; i < weights.size(); i++) {
            weights.add(i, Main.r.nextDouble()*2-1);
            weights.remove(i+1);
        }
    }
    
    public void backprop() {
        if (NODETYPE == Node.type.INPUT || NODETYPE == Node.type.OUTPUT) {
            return;
        }else if (Double.isNaN(error)) {
            error = 0;
        }
        for (Node n : outputs) {
            error += n.askForAdjustments(this);
        }
    }
    
    public void forwardprop () {
        if (NODETYPE == type.INPUT) {
            return;
        }
        if (Double.isNaN(error) || Double.isNaN(sum)) {
            throw new Error("Node:: Error not set. Did you run backprop first? ("
                    +NODETYPE+")");
        }
        double newWeight;
        for(int i = 0; i < inputs.size(); i++) {
            newWeight = weights.get(i) + sys.LEARNSPEED * error * 
                    differential(activation(sum)) * inputs.get(i).output();
            momentum.set(i, momentum.get(i) + (newWeight - weights.get(i)) * sys.MOMENTUM);
            newWeight += momentum.get(i);
            weights.set(i,newWeight);
        }
    }
    
    private void o (Object o) {
        java.lang.System.out.println(o);
    }
    
    @Override
    public String toString () {
        String result = "Node::"+this.NODETYPE+"::"+this.hashCode()+"\n";
        result += "INPUTS:\n";
        for(Node n : inputs) {
            result += "\t"+n.output();
        }
        result += "\nWEIGHTS:\n";
        for(Double w : weights) {
            result += w + ", ";
        }
        result += "\n"+this.output;
        return result;
    }
}
